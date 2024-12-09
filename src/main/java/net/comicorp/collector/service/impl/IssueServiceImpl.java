package net.comicorp.collector.service.impl;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.component.KeyValidator;
import net.comicorp.collector.component.Mapper;
import net.comicorp.collector.domain.repository.IssueRepository;
import net.comicorp.collector.domain.repository.PublisherRepository;
import net.comicorp.collector.domain.repository.TitleRepository;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.service.IssueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static net.comicorp.collector.constant.Constants.*;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final PublisherRepository publisherRepository;

    private final TitleRepository titleRepository;

    private final IssueRepository issueRepository;

    private final KeyValidator keyValidator;

    private final Mapper mapper;

    @Override
    public Page<IssueDTO> getByKeyAndPublisherKeyAndTitleKey(String publisherKey, String titleKey, String key, Boolean variant, Pageable pageable) {

        keyValidator.validateKey(publisherKey, () -> publisherRepository.existsByKey(publisherKey), PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE);
        keyValidator.validateKey(titleKey, () -> titleRepository.existsByKey(titleKey), TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE);
        keyValidator.validateKey(key, () -> issueRepository.existsByKey(key), ISSUE_KEY_NOT_FOUND_EXCEPTION_MESSAGE);

        return issueRepository.findByKeyAndPublisherKeyAndTitleKey(publisherKey, titleKey, key, variant, pageable).map(mapper::toIssueDTO);
    }
}
