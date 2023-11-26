package xyz.krakenkat.collector.controller;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.exception.PublisherKeyNotFoundException;
import xyz.krakenkat.collector.service.TitleService;

import java.util.Optional;

@RestController
@RequestMapping("/titles")
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;

    @GetMapping
    public ResponseEntity<Page<TitleDTO>> retrieveAllTitles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(titleService.getAllTitles(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TitleDTO> retrieveTitleById(
            @PathVariable String id,
            @RequestParam(name = "publisher", required = false) Optional<String> publisherKey)
            throws PublisherKeyNotFoundException {
        return ResponseEntity.ok(titleService.getTitleByKey(id, publisherKey).orElseThrow(NoContentException::new));
    }

    @GetMapping("/{id}/issues")
    public ResponseEntity<Page<IssueDTO>> retrieveIssuesByTitleId(
            @PathVariable ObjectId id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(titleService.getAllIssuesByTitle(id, PageRequest.of(page, size)));
    }
}
