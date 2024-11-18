package net.comicorp.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.controller.PublisherController;
import net.comicorp.collector.dto.PublisherDTO;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.exception.NoContentException;
import net.comicorp.collector.service.PublisherService;
import net.comicorp.collector.service.TitleService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static net.comicorp.collector.util.Utilities.buildPage;
import static net.comicorp.collector.util.Utilities.buildPagedModel;

@RestController
@RequiredArgsConstructor
public class PublisherControllerImpl implements PublisherController {

    private final PublisherService publisherService;

    private final TitleService titleService;

    @Override
    public ResponseEntity<PagedModel<PublisherDTO>> retrieveAllPublishers(int page, int size, String[] sort) {
        Page<PublisherDTO> publisherDTOPage = publisherService.getPublishers(buildPage(page, size, sort));
        return publisherDTOPage.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(publisherDTOPage));
    }

    @Override
    public ResponseEntity<PublisherDTO> retrievePublisherByKey(String key) throws NoContentException {
        return ResponseEntity.ok(publisherService.getPublisherByKey(key));
    }

    @Override
    public ResponseEntity<PagedModel<TitleDTO>> retrieveAllTitlesByKey(String publisherKey, int page, int size, String[] sort) {
        Page<TitleDTO> titleDTOPage = titleService.getAllTitlesByPublisherKey(publisherKey, buildPage(page, size, sort));
        return titleDTOPage.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(titleDTOPage));
    }
}
