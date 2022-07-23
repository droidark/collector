package com.comixtorm.collector.domain.repository;

import com.comixtorm.collector.domain.model.Issue;
import com.comixtorm.collector.domain.repository.custom.CustomIssueRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends MongoRepository<Issue, String>, CustomIssueRepository {

    Page<Issue> findAll(Pageable pageable);
    Page<Issue> findAllByTitle(ObjectId titleId, Pageable pageable);
    Page<Issue> findAllByPublisherKeyAndTitleKey(String publisherKey, String titleKey, boolean variant, Pageable pageable);
    Optional<Issue> findOneByPublisherKeyAndTitleKeyAndIssueKey(String publisherKey, String titleKey, String issueKey);
    Page<Issue> findAllByVariantOfAndVariantIsTrue(ObjectId variantOf, Pageable pageable);
    Page<Issue> findAllVariantsByPublisherKeyAndTitleKeyAndIssueKey(String publisherKey, String titleKey, String issueKey, Pageable pageable);
}
