package net.comicorp.collector.controller;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.dto.PublisherDTO;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.exception.NoContentException;
import net.comicorp.collector.service.PublisherService;
import net.comicorp.collector.service.TitleService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static net.comicorp.collector.util.Utilities.buildPage;
import static net.comicorp.collector.util.Utilities.buildPagedModel;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    private final TitleService titleService;

    @GetMapping
    ResponseEntity<PagedModel<PublisherDTO>> retrieveAllPublishers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort) {
        Page<PublisherDTO> publisherDTOPage = publisherService.getPublishers(buildPage(page, size, sort));
        return publisherDTOPage.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(publisherDTOPage));
    }

    @GetMapping("/{key}")
    ResponseEntity<PublisherDTO> retrievePublisherByKey(
            @PathVariable String key) throws NoContentException {
        return ResponseEntity.ok(publisherService.getPublisherByKey(key));
    }

    @GetMapping("/{publisherKey}/titles")
    ResponseEntity<PagedModel<TitleDTO>> retrieveAllTitlesByKey(
            @PathVariable String publisherKey,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort) {
        Page<TitleDTO> titleDTOPage = titleService.getAllTitlesByPublisherKey(publisherKey, buildPage(page, size, sort));
        return titleDTOPage.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(titleDTOPage));
    }
}
