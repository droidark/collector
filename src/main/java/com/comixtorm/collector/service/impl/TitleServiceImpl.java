package com.comixtorm.collector.service.impl;

import com.comixtorm.collector.domain.model.Title;
import com.comixtorm.collector.domain.model.exception.PublisherKeyNotFoundException;
import com.comixtorm.collector.domain.repository.PublisherRepository;
import com.comixtorm.collector.domain.repository.TitleRepository;
import com.comixtorm.collector.service.TitleService;
import com.comixtorm.collector.util.Constants;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("titleService")
@AllArgsConstructor
public class TitleServiceImpl implements TitleService {

    private PublisherRepository publisherRepository;
    private TitleRepository titleRepository;

    @Override
    public Page<Title> getTitlesByKey(String key, Pageable pageable) {
        return publisherRepository.existsByKey(key)
                ? titleRepository.findAllByPublisherKey(key, pageable)
                : titleRepository.findAllByPublisher(new ObjectId(key), pageable);
    }

    @Override
    public Title getTitleByKey(String key, Optional<String> publisher) throws PublisherKeyNotFoundException {
        return titleRepository.existsByKey(key)
                ? titleRepository.findByPublisherKeyAndTitleKey(publisher.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)), key)
                : titleRepository.findById(new ObjectId(key));
    }
}
