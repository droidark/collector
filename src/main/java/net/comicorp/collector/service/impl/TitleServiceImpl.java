package net.comicorp.collector.service.impl;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.domain.repository.IssueRepository;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.service.KeyValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.comicorp.collector.domain.repository.PublisherRepository;
import net.comicorp.collector.domain.repository.TitleRepository;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.exception.NoContentException;
import net.comicorp.collector.service.MapperService;
import net.comicorp.collector.service.TitleService;

import static net.comicorp.collector.constant.Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE;
import static net.comicorp.collector.constant.Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    private final TitleRepository titleRepository;

    private final PublisherRepository publisherRepository;

    private final IssueRepository issueRepository;

    private final KeyValidator keyValidator;

    private final MapperService mapper;

    @Override
    public Page<TitleDTO> getAllTitlesByPublisherKey(String publisherKey, Pageable pageable) throws FieldNotValidException {
        keyValidator.validateKey(publisherKey, () -> publisherRepository.existsByKey(publisherKey), PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE);
        return titleRepository.findByPublisherKey(publisherKey, pageable).map(mapper::toTitleDTO);
    }

    @Override
    public TitleDTO getTitleByKeyAndPublisherKey(String key, String publisherKey) throws FieldNotValidException, NoContentException {
        keyValidator.validateKey(publisherKey, () -> publisherRepository.existsByKey(publisherKey), PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE);
        keyValidator.validateKey(key, () -> titleRepository.existsByKey(key), TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE);
        return titleRepository.findByKeyAndPublisherKey(key, publisherKey).map(mapper::toTitleDTO).orElseThrow(NoContentException::new);
    }

    @Override
    public Page<IssueDTO> getAllIssuesByPublisherKeyAndTitleKey(String publisherKey, String titleKey, Boolean variant, Pageable pageable) throws FieldNotValidException {
        keyValidator.validateKey(publisherKey, () -> publisherRepository.existsByKey(publisherKey), PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE);
        keyValidator.validateKey(titleKey, () -> titleRepository.existsByKey(titleKey), TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE);
        return issueRepository.findByPublisherKeyAndTitleKey(publisherKey, titleKey, variant, pageable).map(mapper::toIssueDTO);
    }
}
