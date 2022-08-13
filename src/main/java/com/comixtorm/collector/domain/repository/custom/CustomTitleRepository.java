package com.comixtorm.collector.domain.repository.custom;

import com.comixtorm.collector.domain.model.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomTitleRepository {
    Page<Title> findAllByPublisherKey(String publisherKey, Pageable pageable);
    Page<Title> findAllByUsername(String username, Pageable pageable);
    Title findByPublisherKeyAndTitleKey(String publisherKey, String titleKey);
}
