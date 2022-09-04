package xyz.krakenkat.collector.domain.repository.custom;

import xyz.krakenkat.collector.domain.model.Issue;
import xyz.krakenkat.collector.domain.model.query.Ids;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomIssueRepository {
    Page<Issue> findAllByPublisherKeyAndTitleKey(
            String publisherKey,
            String titleKey,
            boolean variant,
            Pageable pageable);

    Optional<Issue> findOneByPublisherKeyAndTitleKeyAndIssueKey(String publisherKey, String titleKey, String issueKey);

    Page<Issue> findAllVariantsByPublisherKeyAndTitleKeyAndIssueKey(
            String publisherKey,
            String titleKey,
            String issueKey,
            Pageable pageable);

    Page<Issue> findAllByUsernameAndTitleId(String username, ObjectId titleId, Pageable pageable);

    Ids findPublisherIdAndTitleByIssueId(ObjectId issueId);
}
