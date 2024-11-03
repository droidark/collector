package xyz.krakenkat.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.controller.PublisherController;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.PublisherService;
import xyz.krakenkat.collector.service.TitleService;

import static xyz.krakenkat.collector.util.Utilities.buildPagedModel;
import static xyz.krakenkat.collector.util.Utilities.buildSortCriteria;

@RestController
@RequiredArgsConstructor
public class PublisherControllerImpl implements PublisherController {

    private final PublisherService publisherService;

    private final TitleService titleService;

    @Override
    public ResponseEntity<PagedModel<PublisherDTO>> retrieveAllPublishers(int page, int size, String[] sort) {
        Page<PublisherDTO> publisherDTOPage = publisherService.getPublishers(PageRequest.of(page, size, buildSortCriteria(sort)));
        return publisherDTOPage.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(publisherDTOPage));
    }

    @Override
    public ResponseEntity<PublisherDTO> retrievePublisherByKey(String key) throws NoContentException {
        return ResponseEntity.ok(publisherService.getPublisherByKey(key));
    }

    @Override
    public ResponseEntity<PagedModel<TitleDTO>> retrieveAllTitlesByKey(String publisherKey, int page, int size, String[] sort) {
        Page<TitleDTO> titleDTOPage = titleService.getAllTitlesByPublisherKey(publisherKey, PageRequest.of(page, size, buildSortCriteria(sort)));
        return titleDTOPage.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(titleDTOPage));
    }
}
