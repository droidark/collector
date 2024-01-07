package xyz.krakenkat.collector.domain.repository;

import xyz.krakenkat.collector.domain.model.Issue;
import xyz.krakenkat.collector.domain.model.query.Ids;
import xyz.krakenkat.collector.domain.repository.custom.CustomIssueRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends MongoRepository<Issue, String>, CustomIssueRepository {

    Page<Issue> findAllByPublisherKeyAndTitleKey(String publisherKey, String titleKey, String variant, Pageable pageable);

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

    boolean existsByKey(String key);

    List<Ids> findIdsByKeys(String publisherKey, String titleKey, List<String> issueKeys);
}
