package xyz.krakenkat.collector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.krakenkat.collector.dto.IssueDTO;

import java.util.Optional;

public interface IssueService {
    Page<IssueDTO> getIssuesByPublisherKeyAndTitleKey(Optional<String> publisherKey,
                                                      Optional<String> titleKey,
                                                      String variant,
                                                      Pageable pageable);

    Optional<IssueDTO> getIssueByPublisherKeyAndTitleKeyAndIssueKey(Optional<String> publisherKey,
                                                       Optional<String> titleKey,
                                                       String issueKey);

    Page<IssueDTO> getVariantIssuesByPublisherKeyAndTitleKeyAndIssueKey(Optional<String> publisherKey,
                                                                     Optional<String> titleKey,
                                                                     String issueKey,
                                                                     Pageable pageable);
}