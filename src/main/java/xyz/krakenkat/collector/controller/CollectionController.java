package xyz.krakenkat.collector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.KeysDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.service.CollectionService;

import java.util.Optional;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping
    public ResponseEntity<Page<TitleDTO>> retrieveCollectedTitles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        return ResponseEntity.ok(collectionService
                .getTitlesByUsername(authentication.getName(), PageRequest.of(page, size)));
    }

    @GetMapping("/{titleKey}/issues")
    public ResponseEntity<Page<IssueDTO>> retrieveCollectedIssuesByPublisherKeyAndTitleKey(
            @PathVariable String titleKey,
            @RequestParam Optional<String> publisher,
            @RequestParam(defaultValue = "false") boolean variant,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        return ResponseEntity.ok(collectionService
                .getIssuesByUsernameAndTitleKey(authentication.getName(), publisher, titleKey, variant, PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<Void> addItemsToCollection(@RequestBody KeysDTO keysDTO, Authentication authentication) {
        collectionService.addItemsToCollection(keysDTO, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeItemsToCollection(@RequestBody KeysDTO keysDTO, Authentication authentication) {
        collectionService.removeItemsToCollection(keysDTO, authentication.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
