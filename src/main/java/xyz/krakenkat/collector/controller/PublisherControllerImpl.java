package xyz.krakenkat.collector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.controller.impl.PublisherController;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.PublisherService;
import xyz.krakenkat.collector.service.TitleService;
import xyz.krakenkat.collector.util.Utilities;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class PublisherControllerImpl implements PublisherController {

    private final PublisherService publisherService;

    private final TitleService titleService;

    @Override
    public ResponseEntity<Page<PublisherDTO>> retrieveAllPublishers(int page, int size, String[] sort) {
        return ResponseEntity.ok(publisherService.getAllPublishers(PageRequest.of(page, size, Utilities.buildSortCriteria(sort))));
    }

    @Override
    public ResponseEntity<PublisherDTO> retrievePublisherByKey(String key) throws NoContentException {
        return ResponseEntity.ok(publisherService.getPublisherByKey(key));
    }

    @Override
    public ResponseEntity<Page<TitleDTO>> retrieveTitlesByKey(String key, int page, int size, String[] sort) {
        return ResponseEntity.ok(titleService.getTitlesByKey(key, PageRequest.of(page, size, Utilities.buildSortCriteria(sort))));
    }
}
