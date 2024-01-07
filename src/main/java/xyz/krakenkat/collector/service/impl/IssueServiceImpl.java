package xyz.krakenkat.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.repository.IssueRepository;
import xyz.krakenkat.collector.domain.repository.PublisherRepository;
import xyz.krakenkat.collector.domain.repository.TitleRepository;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.exception.FieldNotValidException;
import xyz.krakenkat.collector.service.IssueService;
import xyz.krakenkat.collector.util.MapperService;

import static xyz.krakenkat.collector.util.Constants.*;

@Service("issueService")
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final PublisherRepository publisherRepository;

    private final TitleRepository titleRepository;

    private final IssueRepository issueRepository;

    private final MapperService mapper;

    @Override
    public Page<IssueDTO> getIssueByPublisherKeyAndTitleKeyAndIssueKey(
            String publisherKey,
            String titleKey,
            String issueKey,
            String variant,
            Pageable pageable)
            throws FieldNotValidException {
        // Check if exists publisher key
        if (!publisherRepository.existsByKey(publisherKey)) {
            throw new FieldNotValidException(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, publisherKey, "publisherKey");
        }
        // Check if exists title key
        if (!titleRepository.existsByKey(titleKey)) {
            throw new FieldNotValidException(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, titleKey, "titleKey");
        }
        // Check if exists issue key
        if (!issueRepository.existsByKey(issueKey)) {
            throw new FieldNotValidException(ISSUE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, issueKey, "issueKey");
        }
        return issueRepository
                .findAllByPublisherKeyAndTitleKeyAndIssueKey(publisherKey, titleKey, issueKey, variant, pageable)
                .map(mapper::toIssueDTO);
    }
}
