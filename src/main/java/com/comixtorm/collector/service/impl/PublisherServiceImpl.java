package com.comixtorm.collector.service.impl;

import com.comixtorm.collector.domain.model.Publisher;
import com.comixtorm.collector.domain.repository.PublisherRepository;
import com.comixtorm.collector.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("publisherService")
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;

    @Override
    public Page<Publisher> getAllPublishers(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    @Override
    public Optional<Publisher> getPublisherByKey(String key) {
        return publisherRepository.findOneByKey(key);
    }
}
