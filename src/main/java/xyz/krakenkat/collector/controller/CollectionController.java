package xyz.krakenkat.collector.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.KeysDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.service.CollectionService;

import java.util.Optional;

@RestController
@RequestMapping("/collections")
@AllArgsConstructor
public class CollectionController {

    private CollectionService collectionService;

    @GetMapping
    public Page<TitleDTO> retrieveCollectedTitles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        return collectionService.getTitlesByUsername(authentication.getName(), PageRequest.of(page, size));
    }

    @GetMapping("/{titleKey}/issues")
    public Page<IssueDTO> retrieveCollectedIssuesByTitleId(
            @PathVariable String titleKey,
            @RequestParam Optional<String> publisherKey,
            @RequestParam(defaultValue = "false") boolean variant,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        return collectionService.getIssuesByUsernameAndTitleId(authentication.getName(), publisherKey, titleKey, variant, PageRequest.of(page, size));
    }

    @PostMapping
    public void addItemsToCollection(@RequestBody KeysDTO keysDTO, Authentication authentication) {
        collectionService.addItemsToCollection(keysDTO, authentication.getName());
    }

    @DeleteMapping
    public void removeItemsToCollection(@RequestBody KeysDTO keysDTO, Authentication authentication) {
        collectionService.removeItemsToCollection(keysDTO, authentication.getName());
    }
}
