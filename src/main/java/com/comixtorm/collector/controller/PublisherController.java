package com.comixtorm.collector.controller;

import com.comixtorm.collector.domain.model.Publisher;
import com.comixtorm.collector.domain.model.Title;
import com.comixtorm.collector.domain.model.exception.NoContentException;
import com.comixtorm.collector.service.PublisherService;
import com.comixtorm.collector.service.TitleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
@AllArgsConstructor
public class PublisherController {

    private PublisherService publisherService;
    private TitleService titleService;

    @GetMapping
    public Page<Publisher> retrieveAllPublishers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return publisherService.getAllPublishers(PageRequest.of(page, size));
    }

    @GetMapping("/{key}")
    public Publisher retrievePublisherByKey(@PathVariable String key) throws NoContentException {
        return publisherService.getPublisherByKey(key).orElseThrow(() -> new NoContentException());
    }

    @GetMapping("/{key}/titles")
    public Page<Title> retrieveTitlesByKey(
            @PathVariable String key,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return titleService.getTitlesByKey(key, PageRequest.of(page, size));
    }
}
