package xyz.krakenkat.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.controller.TitleController;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.FieldNotValidException;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.TitleService;
import xyz.krakenkat.collector.util.Utilities;

@RestController
@RequiredArgsConstructor
public class TitleControllerImpl implements TitleController {

    private final TitleService titleService;

    @Override
    public ResponseEntity<TitleDTO> retrieveTitleByTitleKeyAndPublisherKey(String titleKey, String publisherKey)
            throws FieldNotValidException {
        return ResponseEntity
                .ok(titleService
                        .getTitleByTitleKeyAndPublisherKey(titleKey, publisherKey).orElseThrow(NoContentException::new));
    }

    @Override
    public ResponseEntity<Page<IssueDTO>> retrieveIssuesByTitleKeyAndPublisherKey(String titleKey, String publisherKey, String variant, int page, int size, String[] sort) {
        Page<IssueDTO> issues = titleService.getAllIssuesByTitleKeyAndPublisherKey(publisherKey, titleKey, variant, PageRequest.of(page, size, Utilities.buildSortCriteria(sort)));
        if (!issues.isEmpty()) {
            return ResponseEntity.ok(issues);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
