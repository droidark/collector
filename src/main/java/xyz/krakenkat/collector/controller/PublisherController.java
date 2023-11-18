package xyz.krakenkat.collector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.PublisherService;
import xyz.krakenkat.collector.service.TitleService;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    private final TitleService titleService;

    @GetMapping
    public Page<PublisherDTO> retrieveAllPublishers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return publisherService.getAllPublishers(PageRequest.of(page, size));
    }

    @GetMapping("/{key}")
    public PublisherDTO retrievePublisherByKey(@PathVariable String key) throws NoContentException {
        return publisherService.getPublisherByKey(key);
    }

    @GetMapping("/{key}/titles")
    public Page<TitleDTO> retrieveTitlesByKey(
            @PathVariable String key,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return titleService.getTitlesByKey(key, PageRequest.of(page, size));
    }
}
