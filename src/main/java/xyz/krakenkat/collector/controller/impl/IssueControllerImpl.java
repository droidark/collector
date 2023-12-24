package xyz.krakenkat.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.controller.IssueController;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.service.IssueService;

@RestController
@RequiredArgsConstructor
public class IssueControllerImpl implements IssueController {

    private final IssueService issueService;

    @Override
    public ResponseEntity<IssueDTO> retrieveIssueByPublisherKeyTitleKeyAndIssueKey(String publisherKey, String titleKey, String issueKey) {
        return null;
    }
}
