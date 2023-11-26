package xyz.krakenkat.collector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.exception.PublisherKeyNotFoundException;
import xyz.krakenkat.collector.exception.TitleKeyNotFoundException;
import xyz.krakenkat.collector.service.IssueService;

import java.util.Optional;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<Page<IssueDTO>> retrieveAllIssues(
            @RequestParam(name = "publisher", required = false) Optional<String> publisherKey,
            @RequestParam(name = "title", required = false) Optional<String> titleKey,
            @RequestParam(name = "variant", required = false, defaultValue = "false") boolean variant,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws PublisherKeyNotFoundException, TitleKeyNotFoundException {
        return ResponseEntity.ok(issueService
                .getIssuesByPublisherKeyAndTitleKey(publisherKey, titleKey, variant, PageRequest.of(page, size)));
    }

    @GetMapping("/{key}")
    public ResponseEntity<IssueDTO> retrieveIssueById(
            @PathVariable String key,
            @RequestParam(name = "publisher", required = false) Optional<String> publisherKey,
            @RequestParam(name = "title", required = false) Optional<String> titleKey) throws
            PublisherKeyNotFoundException,
            TitleKeyNotFoundException, NoContentException {
        return ResponseEntity.ok(issueService.getIssueByPublisherKeyAndTitleKeyAndIssueKey(publisherKey, titleKey, key)
                .orElseThrow(NoContentException::new));
    }

    @GetMapping("/{key}/variants")
    public ResponseEntity<Page<IssueDTO>> retrieveVariantIssuesById(
            @PathVariable String key,
            @RequestParam(name = "publisher", required = false) Optional<String> publisherKey,
            @RequestParam(name = "title", required = false) Optional<String> titleKey,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws
            PublisherKeyNotFoundException,
            TitleKeyNotFoundException{
        return ResponseEntity.ok(issueService.getVariantIssuesByPublisherKeyAndTitleKeyAndIssueKey(publisherKey,
                titleKey, key, PageRequest.of(page, size)));
    }
}
