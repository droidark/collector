package net.comicorp.collector.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.comicorp.collector.component.Mapper;
import net.comicorp.collector.domain.repository.PublisherRepository;
import net.comicorp.collector.dto.PublisherDTO;
import net.comicorp.collector.exception.NoContentException;
import net.comicorp.collector.service.PublisherService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    private final Mapper mapper;

    @Override
    public Page<PublisherDTO> getPublishers(Pageable pageable) {
        return publisherRepository.findAll(pageable).map(mapper::toPublisherDTO);
    }

    @Override
    public PublisherDTO getPublisherByKey(String key) throws NoContentException {
        return publisherRepository.findByKey(key).map(mapper::toPublisherDTO).orElseThrow(NoContentException::new);
    }

    @Override
    @Cacheable(value = "publishers")
    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll().stream().map(mapper::toPublisherDTO).toList();
    }
}
