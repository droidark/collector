package xyz.krakenkat.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.controller.TitleController;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.FieldNotValidException;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.TitleService;

@RestController
@RequiredArgsConstructor
public class TitleControllerImpl implements TitleController {

    private final TitleService titleService;

    @Override
    public ResponseEntity<TitleDTO> retrieveByKeyAndPublisherKey(String key, String publisherKey) throws FieldNotValidException, NoContentException {
        return ResponseEntity.ok(titleService.getTitleByKeyAndPublisherKey(key, publisherKey));
    }

    @Override
    public ResponseEntity<PagedModel<IssueDTO>> retrieveIssuesByTitleKeyAndPublisherKey(String titleKey, String publisherKey, String variant, int page, int size, String[] sort) {
        return null;
    }
}
