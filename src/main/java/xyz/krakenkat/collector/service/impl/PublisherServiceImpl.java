package xyz.krakenkat.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.util.MapperService;
import xyz.krakenkat.collector.domain.repository.PublisherRepository;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.PublisherService;

@Service("publisherService")
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    private final MapperService mapper;

    @Override
    public Page<PublisherDTO> getAllPublishers(Pageable pageable) {
        return publisherRepository
                .findAll(pageable)
                .map(mapper::toPublisherDTO);
    }

    @Override
    public PublisherDTO getPublisherByKey(String key) throws NoContentException {
        return publisherRepository
                .findOneByKey(key)
                .map(mapper::toPublisherDTO)
                .orElseThrow(NoContentException::new);
    }
}
