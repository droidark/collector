package com.comixtorm.collector.controller;

import com.comixtorm.collector.domain.model.Issue;
import com.comixtorm.collector.domain.model.Title;
import com.comixtorm.collector.domain.model.exception.PublisherKeyNotFoundException;
import com.comixtorm.collector.domain.repository.IssueRepository;
import com.comixtorm.collector.domain.repository.TitleRepository;
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

    private TitleRepository titleRepository;
    private TitleService titleService;
    private IssueRepository issueRepository;

    @GetMapping
    public @ResponseBody Page<Title> retrieveAllTitles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return titleRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public @ResponseBody Title retrieveTitleById(
            @PathVariable String id,
            @RequestParam(name = "publisher", required = false) Optional<String> publisherKey)
            throws PublisherKeyNotFoundException {
        return titleService.getTitleByKey(id, publisherKey);
    }

    @GetMapping("/{id}/issues")
    public @ResponseBody Page<Issue> retrieveIssuesByTitleId(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return issueRepository.findAllByTitle(new ObjectId(id), PageRequest.of(page, size));
    }
}
