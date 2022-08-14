package com.comixtorm.collector.service.impl;

import com.comixtorm.collector.domain.model.Issue;
import com.comixtorm.collector.domain.model.Item;
import com.comixtorm.collector.domain.model.Title;
import com.comixtorm.collector.domain.model.User;
import com.comixtorm.collector.domain.model.query.Ids;
import com.comixtorm.collector.domain.repository.IssueRepository;
import com.comixtorm.collector.domain.repository.TitleRepository;
import com.comixtorm.collector.domain.repository.UserRepository;
import com.comixtorm.collector.service.CollectionService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service("collectionService")
@AllArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    UserRepository userRepository;
    TitleRepository titleRepository;
    IssueRepository issueRepository;

    @Override
    public Page<Title> getTitlesByUsername(String username, Pageable pageable) {
        return titleRepository.findAllByUsername(username, pageable);
    }

    @Override
    public Page<Issue> getIssuesByUsernameAndTitleId(String username, ObjectId titleId, Pageable pageable) {
        return issueRepository.findAllByUsernameAndTitleId(username, titleId, pageable);
    }

    @Override
    public void addItemsToCollection(List<Item> items, String username) {
        User user = userRepository.findByUsername(username).get();
        List<Item> collection = Optional.ofNullable(user.getItems()).orElse(new ArrayList<>());
        items = items
                .stream()
                .filter(i -> !collection.contains(i))
                .map(this::buildItem)
                .collect(Collectors.toList());
        collection.addAll(items);
        user.setItems(collection);
        userRepository.save(user);
    }

    @Override
    public void removeItemsToCollection(List<Item> items, String username) {
        User user = userRepository.findByUsername(username).get();
        List<Item> collection = Optional.ofNullable(user.getItems()).orElse(new ArrayList<>());
        collection.removeAll(items);
        user.setItems(collection);
        userRepository.save(user);
    }

    private Item buildItem(Item item) {
        Ids ids = issueRepository.findPublisherIdAndTitleByIssueId(item.getIssueId());
        item.setPublisherId(ids.getPublisherId());
        item.setTitleId(ids.getTitleId());
        item.setCollectedDate(Date.from(Instant.now()));
        return item;
    }
}
