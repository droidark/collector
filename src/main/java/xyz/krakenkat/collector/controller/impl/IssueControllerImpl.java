package xyz.krakenkat.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.controller.IssueController;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.service.IssueService;

import static xyz.krakenkat.collector.util.Utilities.buildSortCriteria;

@RestController
@RequiredArgsConstructor
public class IssueControllerImpl implements IssueController {

    private final IssueService issueService;

    @Override
    public ResponseEntity<Page<IssueDTO>> retrieveIssueByPublisherKeyTitleKeyAndIssueKey(
            String issueKey,
            String publisherKey,
            String titleKey, String variant,
            int page,
            int size,
            String[] sort) {

        Page<IssueDTO> issues = issueService.getIssueByPublisherKeyAndTitleKeyAndIssueKey(
                publisherKey,
                titleKey,
                issueKey,
                variant,
                PageRequest.of(page, size, buildSortCriteria(sort)));

        if (!issues.isEmpty()) {
            return ResponseEntity.ok(issues);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
