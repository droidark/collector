package com.comixtorm.collector.service;

import com.comixtorm.collector.domain.model.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PublisherService {
    Page<Publisher> getAllPublishers(Pageable pageable);
    Optional<Publisher> getPublisherByKey(String key);
}
