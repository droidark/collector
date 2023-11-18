package xyz.krakenkat.collector.controller;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<TitleDTO> retrieveAllTitles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return titleService.getAllTitles(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public TitleDTO retrieveTitleById(
            @PathVariable String id,
            @RequestParam(name = "publisher", required = false) Optional<String> publisherKey)
            throws PublisherKeyNotFoundException {
        return titleService.getTitleByKey(id, publisherKey).orElseThrow(NoContentException::new);
    }

    @GetMapping("/{id}/issues")
    public Page<IssueDTO> retrieveIssuesByTitleId(
            @PathVariable ObjectId id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return titleService.getAllIssuesByTitle(id, PageRequest.of(page, size));
    }
}
