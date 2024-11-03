package xyz.krakenkat.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.constant.Constants;
import xyz.krakenkat.collector.domain.repository.PublisherRepository;
import xyz.krakenkat.collector.domain.repository.TitleRepository;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.FieldNotValidException;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.MapperService;
import xyz.krakenkat.collector.service.TitleService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    private final TitleRepository titleRepository;

    private final PublisherRepository publisherRepository;

    private final MapperService mapper;


    @Override
    public TitleDTO getTitleById(Long id) throws NoContentException {
        return titleRepository.findById(id).map(mapper::toTitleDTO).orElseThrow(NoContentException::new);
    }

    @Override
    public Page<TitleDTO> getAllTitlesByPublisherKey(String publisherKey, Pageable pageable) {

        if (Boolean.FALSE.equals(publisherRepository.existsByKey(publisherKey))) {
            throw new FieldNotValidException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, publisherKey, "publisherKey");
        }

        return titleRepository.findByPublisherKey(publisherKey, pageable).map(mapper::toTitleDTO);
    }

    @Override
    public Optional<TitleDTO> getTitleByTitleKeyAndPublisherKey(String titleKey, String publisherKey) throws FieldNotValidException {
        if (Boolean.FALSE.equals(publisherRepository.existsByKey(publisherKey))) {
            throw new FieldNotValidException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, publisherKey, "publisherKey");
        }

        if (Boolean.FALSE.equals(titleRepository.existsByKey(titleKey))) {
            throw new FieldNotValidException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, titleKey, "titleKey");
        }

        return titleRepository.findByKeyAndPublisherKey(titleKey, publisherKey).map(mapper::toTitleDTO);
    }
}
