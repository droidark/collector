package com.comixtorm.collector.service;

import com.comixtorm.collector.domain.model.Item;
import com.comixtorm.collector.domain.model.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollectionService {
    Page<Title> getTitlesByUsername(String username, Pageable pageable);
    void addItemsToCollection(List<Item> items, String username);
    void removeItemsToCollection(List<Item> items, String username);
}
