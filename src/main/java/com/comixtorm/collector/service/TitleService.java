package com.comixtorm.collector.service;

import com.comixtorm.collector.domain.model.Title;
import com.comixtorm.collector.domain.model.exception.PublisherKeyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TitleService {
    Page<Title> getTitlesByKey(String key, Pageable pageable);
    Title getTitleByKey(String key, Optional<String> publisher) throws PublisherKeyNotFoundException;
}
