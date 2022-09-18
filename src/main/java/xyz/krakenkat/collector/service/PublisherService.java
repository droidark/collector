package xyz.krakenkat.collector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.dto.PublisherDTO;

public interface PublisherService {
    Page<PublisherDTO> getAllPublishers(Pageable pageable);
    PublisherDTO getPublisherByKey(String key) throws NoContentException;
}
