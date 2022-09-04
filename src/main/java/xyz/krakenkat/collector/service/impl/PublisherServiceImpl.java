package xyz.krakenkat.collector.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.model.exception.NoContentException;
import xyz.krakenkat.collector.domain.repository.PublisherRepository;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.mapper.Convert;
import xyz.krakenkat.collector.service.PublisherService;

@Service("publisherService")
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;

    @Override
    public Page<PublisherDTO> getAllPublishers(Pageable pageable) {
        return publisherRepository
                .findAll(pageable)
                .map(Convert::toPublisherDTO);
    }

    @Override
    public PublisherDTO getPublisherByKey(String key) throws NoContentException {
        return publisherRepository
                .findOneByKey(key)
                .map(Convert::toPublisherDTO)
                .orElseThrow(NoContentException::new);
    }
}
