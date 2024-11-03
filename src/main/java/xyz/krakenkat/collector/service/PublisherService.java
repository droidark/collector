package xyz.krakenkat.collector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.exception.NoContentException;

public interface PublisherService {

    Page<PublisherDTO> getPublishers(Pageable pageable);

    PublisherDTO getPublisherByKey(String key) throws NoContentException;
}
