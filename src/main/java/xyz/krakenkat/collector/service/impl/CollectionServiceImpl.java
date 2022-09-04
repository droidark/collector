package xyz.krakenkat.collector.service.impl;

import xyz.krakenkat.collector.domain.model.Issue;
import xyz.krakenkat.collector.domain.model.Item;
import xyz.krakenkat.collector.domain.model.Title;
import xyz.krakenkat.collector.domain.model.User;
import xyz.krakenkat.collector.domain.model.query.Ids;
import xyz.krakenkat.collector.domain.repository.IssueRepository;
import xyz.krakenkat.collector.domain.repository.TitleRepository;
import xyz.krakenkat.collector.domain.repository.UserRepository;
import xyz.krakenkat.collector.service.CollectionService;
import xyz.krakenkat.collector.util.Constants;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("collectionService")
@AllArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    UserRepository userRepository;
    TitleRepository titleRepository;
    IssueRepository issueRepository;

    @Override
    public Page<Title> getTitlesByUsername(String username, Pageable pageable) {
        return titleRepository.findAllByUsername(username, pageable);
    }

    @Override
    public Page<Issue> getIssuesByUsernameAndTitleId(String username, ObjectId titleId, Pageable pageable) {
        return issueRepository.findAllByUsernameAndTitleId(username, titleId, pageable);
    }

    @Override
    public void addItemsToCollection(List<Item> items, String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND_EXCEPTION_MESSAGE, username)));
        List<Item> collection = Optional.ofNullable(user.getItems()).orElse(new ArrayList<>());
        items = items
                .stream()
                .filter(i -> !collection.contains(i))
                .map(this::buildItem)
                .toList();
        collection.addAll(items);
        user.setItems(collection);
        userRepository.save(user);
    }

    @Override
    public void removeItemsToCollection(List<Item> items, String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND_EXCEPTION_MESSAGE, username)));
        List<Item> collection = Optional.ofNullable(user.getItems()).orElse(new ArrayList<>());
        collection.removeAll(items);
        user.setItems(collection);
        userRepository.save(user);
    }

    private Item buildItem(Item item) {
        Ids ids = issueRepository.findPublisherIdAndTitleByIssueId(item.getIssueId());
        item.setPublisherId(ids.getPublisherId());
        item.setTitleId(ids.getTitleId());
        item.setCollectedDate(Date.from(Instant.now()));
        return item;
    }
}
