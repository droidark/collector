package net.comicorp.collector.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.comicorp.collector.dto.CollectionDTO;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.service.CollectionService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static net.comicorp.collector.util.Utilities.buildPage;
import static net.comicorp.collector.util.Utilities.buildPagedModel;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
@Slf4j
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping
    ResponseEntity<PagedModel<TitleDTO>> retrieveCollectedTitles(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort) {
        Page<TitleDTO> titles = collectionService.getTitlesByUsername(authentication.getName(), buildPage(page, size, sort));
        return titles.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(titles));
    }

    @GetMapping("/{titleKey}/issues")
    ResponseEntity<PagedModel<IssueDTO>> retrieveCollectedIssues(
            Authentication authentication,
            @PathVariable final String titleKey,
            @RequestParam String publisher,
            @RequestParam(required = false) Boolean collected,
            @RequestParam(required = false) Boolean variant,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "number,asc") String[] sort) {
        Page<IssueDTO> issues = collectionService.getIssuesWithDynamicFilters(authentication.getName(), titleKey, publisher, collected, variant, buildPage(page, size, sort));
        return issues.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(issues));
    }

    @PostMapping
    ResponseEntity<Void> addIssueToCollection(@RequestBody CollectionDTO collectionDTO, Authentication authentication) {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build() + "/collections/" +
                collectionDTO.getTitleKey() +
                "/issues" +
                "?publisher=" +
                collectionDTO.getPublisherKey() +
                "&collected=true";

        collectionService.addItemToCollection(authentication.getName(), collectionDTO);
        return ResponseEntity.created(URI.create(baseUrl)).build();
    }

    @DeleteMapping
    ResponseEntity<Void> removeIssueFromCollection(@RequestBody CollectionDTO collectionDTO, Authentication authentication) {
        collectionService.removeItemFromCollection(authentication.getName(), collectionDTO);
        return ResponseEntity.noContent().build();
    }
}
