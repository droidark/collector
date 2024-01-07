package xyz.krakenkat.collector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.exception.FieldNotValidException;

public interface IssueService {
    Page<IssueDTO> getIssueByPublisherKeyAndTitleKeyAndIssueKey(String publisherKey, String titleKey, String issueKey, String variant, Pageable pageable) throws FieldNotValidException;
}