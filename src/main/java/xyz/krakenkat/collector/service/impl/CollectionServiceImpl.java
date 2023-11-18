package xyz.krakenkat.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.model.Item;
import xyz.krakenkat.collector.domain.model.User;
import xyz.krakenkat.collector.domain.repository.IssueRepository;
import xyz.krakenkat.collector.domain.repository.TitleRepository;
import xyz.krakenkat.collector.domain.repository.UserRepository;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.KeysDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.PublisherKeyNotFoundException;
import xyz.krakenkat.collector.service.CollectionService;
import xyz.krakenkat.collector.util.Constants;
import xyz.krakenkat.collector.util.MapperService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("collectionService")
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final UserRepository userRepository;

    private final TitleRepository titleRepository;

    private final IssueRepository issueRepository;

    private final MapperService mapper;

    @Override
    public Page<TitleDTO> getTitlesByUsername(String username, Pageable pageable) {
        return titleRepository.findAllByUsername(username, pageable).map(mapper::toTitleDTO);
    }

    @Override
    public Page<IssueDTO> getIssuesByUsernameAndTitleId(String username, Optional<String> publisherKey, String titleKey, boolean variant, Pageable pageable) {
        return issueRepository.findAllByUsernameAndPublisherKeyAndTitleKey(
                username,
                publisherKey.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                titleKey,
                variant,
                pageable).map(mapper::toIssueDTO);
    }

    @Override
    public void addItemsToCollection(KeysDTO keysDTO, String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND_EXCEPTION_MESSAGE, username)));

        List<Item> collection = Optional
                .ofNullable(user.getItems())
                .orElse(new ArrayList<>());

        collection.addAll(buildItem(keysDTO)
                .stream()
                .filter(i -> !collection.contains(i))
                .toList());

        user.setItems(collection);
        userRepository.save(user);
    }

    @Override
    public void removeItemsToCollection(KeysDTO keysDTO, String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND_EXCEPTION_MESSAGE, username)));

        List<Item> collection = Optional
                .ofNullable(user.getItems())
                .orElse(new ArrayList<>());

        collection.removeAll(buildItem(keysDTO));
        user.setItems(collection);
        userRepository.save(user);
    }

    private List<Item> buildItem(KeysDTO keysDTO) {
        return issueRepository
                .findIdsByKeys(keysDTO.getPublisher(), keysDTO.getTitle(), keysDTO.getIssues())
                .stream()
                .map(i -> Item
                        .builder()
                        .publisherId(i.getPublisherId())
                        .titleId(i.getTitleId())
                        .issueId(i.getIssueId())
                        .collectedDate(Date.from(Instant.now()))
                        .build())
                .toList();
    }
}
