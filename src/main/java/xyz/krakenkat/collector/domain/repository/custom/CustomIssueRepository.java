package xyz.krakenkat.collector.domain.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.krakenkat.collector.domain.model.Issue;
import xyz.krakenkat.collector.domain.model.query.Ids;

import java.util.List;
import java.util.Optional;

public interface CustomIssueRepository {
    Page<Issue> findAllByPublisherKeyAndTitleKey(
            String publisherKey,
            String titleKey,
            String variant,
            Pageable pageable);

    Page<Issue> findAllByPublisherKeyAndTitleKeyAndIssueKey(
            String publisherKey,
            String titleKey,
            String issueKey,
            String variant,
            Pageable pageable);

    Page<Issue> findAllByUsernameAndPublisherKeyAndTitleKey(
            String username,
            String publisherKey,
            String titleKey,
            boolean variant,
            Pageable pageable);

    List<Ids> findIdsByKeys(String publisherKey, String titleKey, List<String> issueKeys);
}
