package net.comicorp.collector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.comicorp.collector.dto.PublisherDTO;
import net.comicorp.collector.exception.NoContentException;

public interface PublisherService {

    Page<PublisherDTO> getPublishers(Pageable pageable);

    PublisherDTO getPublisherByKey(String key) throws NoContentException;
}
