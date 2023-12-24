package xyz.krakenkat.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.repository.IssueRepository;
import xyz.krakenkat.collector.domain.repository.PublisherRepository;
import xyz.krakenkat.collector.domain.repository.TitleRepository;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.FieldNotValidException;
import xyz.krakenkat.collector.exception.PublisherKeyNotFoundException;
import xyz.krakenkat.collector.exception.TitleKeyNotFoundException;
import xyz.krakenkat.collector.service.TitleService;
import xyz.krakenkat.collector.util.Constants;
import xyz.krakenkat.collector.util.MapperService;

import java.util.Optional;

@Service("titleService")
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    private final PublisherRepository publisherRepository;

    private final TitleRepository titleRepository;

    private final IssueRepository issueRepository;

    private final MapperService mapper;

    @Override
    public Page<IssueDTO> getAllIssuesByTitleKeyAndPublisherKey(String publisherKey, String titleKey, String variant, Pageable pageable) {
        // Check if exists publisher key
        if (!publisherRepository.existsByKey(publisherKey)) {
            throw new FieldNotValidException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, publisherKey, "publisherKey");
        }
        // Check if exists title key
        if (!titleRepository.existsByKey(titleKey)) {
            throw new FieldNotValidException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, titleKey, "titleKey");
        }
        return issueRepository.findAllByPublisherKeyAndTitleKey(publisherKey, titleKey, variant, pageable).map(mapper::toIssueDTO);
    }

    @Override
    public Page<TitleDTO> getTitlesByKey(String key, Pageable pageable) {
        if (!publisherRepository.existsByKey(key)) {
            throw new FieldNotValidException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, key, "titleKey");
        }
        return titleRepository.findAllByPublisherKey(key, pageable).map(mapper::toTitleDTO);
    }

    @Override
    public Optional<TitleDTO> getTitleByTitleKeyAndPublisherKey(String titleKey, String publisherKey) throws FieldNotValidException {
        // Check if exists publisher key
        if (!publisherRepository.existsByKey(publisherKey)) {
            throw new FieldNotValidException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, publisherKey, "publisherKey");
        }
        // Check if exists title key
        if (!titleRepository.existsByKey(titleKey)) {
            throw new FieldNotValidException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, titleKey, "titleKey");
        }
        return titleRepository.findByPublisherKeyAndTitleKey(publisherKey, titleKey).map(mapper::toTitleDTO);
    }
}
