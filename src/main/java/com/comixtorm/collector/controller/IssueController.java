package com.comixtorm.collector.controller;

import com.comixtorm.collector.domain.model.Issue;
import com.comixtorm.collector.domain.model.exception.NoContentException;
import com.comixtorm.collector.domain.model.exception.PublisherKeyNotFoundException;
import com.comixtorm.collector.domain.model.exception.TitleKeyNotFoundException;
import com.comixtorm.collector.service.IssueService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/issues")
@AllArgsConstructor
public class IssueController {

    private IssueService issueService;

    @GetMapping
    public @ResponseBody Page<Issue> retrieveAllIssues(
            @RequestParam(name = "publisher", required = false) Optional<String> publisherKey,
            @RequestParam(name = "title", required = false) Optional<String> titleKey,
            @RequestParam(name = "variant", required = false, defaultValue = "false") boolean variant,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws PublisherKeyNotFoundException, TitleKeyNotFoundException {
        return issueService.getIssuesByPublisherKeyAndTitleKey(publisherKey, titleKey, variant, PageRequest.of(page, size));
    }

    @GetMapping("/{key}")
    public @ResponseBody Issue retrieveIssueById(
            @PathVariable String key,
            @RequestParam(name = "publisher", required = false) Optional<String> publisherKey,
            @RequestParam(name = "title", required = false) Optional<String> titleKey) throws
            PublisherKeyNotFoundException,
            TitleKeyNotFoundException, NoContentException {
        return issueService.getIssueByPublisherKeyAndTitleKeyAndIssueKey(publisherKey, titleKey, key)
                .orElseThrow(() -> new NoContentException());
    }

    @GetMapping("/{key}/variants")
    public @ResponseBody Page<Issue> retrieveVariantIssuesById(
            @PathVariable String key,
            @RequestParam(name = "publisher", required = false) Optional<String> publisherKey,
            @RequestParam(name = "title", required = false) Optional<String> titleKey,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws
            PublisherKeyNotFoundException,
            TitleKeyNotFoundException{
        return issueService.getVariantIssuesByPublisherKeyAndTitleKeyAndIssueKey(publisherKey,
                titleKey, key, PageRequest.of(page, size));
    }
}
