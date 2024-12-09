package net.comicorp.collector.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.comicorp.collector.component.Mapper;
import net.comicorp.collector.domain.model.Issue;
import net.comicorp.collector.domain.model.User;
import net.comicorp.collector.domain.repository.IssueRepository;
import net.comicorp.collector.domain.repository.TitleRepository;
import net.comicorp.collector.domain.repository.UserRepository;
import net.comicorp.collector.dto.CollectionDTO;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.service.CollectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final TitleRepository titleRepository;

    private final IssueRepository issueRepository;

    private final UserRepository userRepository;

    private final Mapper mapper;

    @Override
    public Page<TitleDTO> getTitlesByUsername(String username, Pageable pageable) {
        return titleRepository.findByUsername(username, pageable).map(mapper::toTitleDTO);
    }

    @Override
    public Page<IssueDTO> getIssuesWithDynamicFilters(String username, String titleKey, String publisherKey, Boolean collected, Boolean variant, Pageable pageable) {
        return issueRepository.findIssuesWithDynamicFilters(username, titleKey, publisherKey, collected, variant, pageable);
    }

    @Override
    public void addItemToCollection(String username, CollectionDTO collectionDTO) {
        // Get selected issues from catalog
        Set<Issue> requiredIssues = issueRepository.findByTitlePublisherAndKeys(collectionDTO.getTitleKey(), collectionDTO.getPublisherKey(), collectionDTO.getIssuesKeys());

        if (!requiredIssues.isEmpty()) {
            log.info("Adding issues to collection");
            User user = userRepository.findByUsername(username).orElseThrow();
            user.getIssues().removeIf(requiredIssues::contains);
            userRepository.save(user);
        }
    }

    @Override
    public void removeItemFromCollection(String username, CollectionDTO collectionDTO) {
        Set<Issue> requiredIssues = issueRepository.findByTitlePublisherAndKeys(collectionDTO.getTitleKey(), collectionDTO.getPublisherKey(), collectionDTO.getIssuesKeys());

        if (!requiredIssues.isEmpty()) {
            log.info("Deleting issues to collection");
            User user = userRepository.findByUsername(username).orElseThrow();
            user.getIssues().removeIf(requiredIssues::contains);
            userRepository.save(user);
        }
    }
}
