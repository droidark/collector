package xyz.krakenkat.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.controller.PublisherController;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.PublisherService;

import static xyz.krakenkat.collector.util.Utilities.buildSortCriteria;

@RestController
@RequiredArgsConstructor
public class PublisherControllerImpl implements PublisherController {

    private final PublisherService publisherService;

    @Override
    public ResponseEntity<Page<PublisherDTO>> retrieveAllPublishers(int page, int size, String[] sort) {
        Page<PublisherDTO> publisherDTOPage = publisherService.getPublishers(PageRequest.of(page, size, buildSortCriteria(sort)));
        if (!publisherDTOPage.isEmpty()) {
            return ResponseEntity.ok(publisherDTOPage);
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PublisherDTO> retrievePublisherByKey(String key) throws NoContentException {
        return ResponseEntity.ok(publisherService.getPublisherByKey(key));
    }
}
