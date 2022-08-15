package com.comixtorm.collector.controller;

import com.comixtorm.collector.domain.model.Issue;
import com.comixtorm.collector.domain.model.Item;
import com.comixtorm.collector.domain.model.Title;
import com.comixtorm.collector.service.CollectionService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
@AllArgsConstructor
public class CollectionController {

    private CollectionService collectionService;

    @GetMapping
    public Page<Title> retrieveCollectedTitles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        return collectionService.getTitlesByUsername(authentication.getName(), PageRequest.of(page, size));
    }

    @GetMapping("/{titleId}/issues")
    public Page<Issue> retrieveCollectedIssuesByTitleId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable ObjectId titleId,
            Authentication authentication) {
        return collectionService.getIssuesByUsernameAndTitleId(authentication.getName(), titleId, PageRequest.of(page, size));
    }

    @PostMapping
    public void addItemsToCollection(@RequestBody List<Item> items, Authentication authentication) {
        collectionService.addItemsToCollection(items, authentication.getName());
    }

    @DeleteMapping
    public void removeItemsToCollection(@RequestBody List<Item> items, Authentication authentication) {
        collectionService.removeItemsToCollection(items, authentication.getName());
    }
}
