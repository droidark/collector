package com.comixtorm.collector.service;

import com.comixtorm.collector.domain.model.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IssueService {
    Page<Issue> getIssuesByPublisherKeyAndTitleKey(Optional<String> publisherKey,
                                                      Optional<String> titleKey,
                                                      boolean variant,
                                                      Pageable pageable);

    Optional<Issue> getIssueByPublisherKeyAndTitleKeyAndIssueKey(Optional<String> publisherKey,
                                                       Optional<String> titleKey,
                                                       String issueKey);

    Page<Issue> getVariantIssuesByPublisherKeyAndTitleKeyAndIssueKey(Optional<String> publisherKey,
                                                                     Optional<String> titleKey,
                                                                     String issueKey,
                                                                     Pageable pageable);
}