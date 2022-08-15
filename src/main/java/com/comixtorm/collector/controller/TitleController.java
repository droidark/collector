package com.comixtorm.collector.controller;

import com.comixtorm.collector.domain.model.Issue;
import com.comixtorm.collector.domain.model.Title;
import com.comixtorm.collector.domain.model.exception.PublisherKeyNotFoundException;
import com.comixtorm.collector.service.TitleService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/titles")
@AllArgsConstructor
public class TitleController {

    private TitleService titleService;

    @GetMapping
    public Page<Title> retrieveAllTitles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return titleService.getAllTitles(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Title retrieveTitleById(
            @PathVariable String id,
            @RequestParam(name = "publisher", required = false) Optional<String> publisherKey)
            throws PublisherKeyNotFoundException {
        return titleService.getTitleByKey(id, publisherKey);
    }

    @GetMapping("/{id}/issues")
    public Page<Issue> retrieveIssuesByTitleId(
            @PathVariable ObjectId id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return titleService.getAllIssuesByTitle(id, PageRequest.of(page, size));
    }
}
