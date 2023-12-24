package xyz.krakenkat.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.repository.IssueRepository;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.exception.PublisherKeyNotFoundException;
import xyz.krakenkat.collector.exception.TitleKeyNotFoundException;
import xyz.krakenkat.collector.service.IssueService;
import xyz.krakenkat.collector.util.Constants;
import xyz.krakenkat.collector.util.MapperService;

import java.util.Optional;

@Service("issueService")
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;

    private final MapperService mapper;

    @Override
    public Page<IssueDTO> getIssuesByPublisherKeyAndTitleKey(Optional<String> publisherKey,
                                                             Optional<String> titleKey,
                                                             String variant,
                                                             Pageable pageable) throws PublisherKeyNotFoundException, TitleKeyNotFoundException {
        return (publisherKey.isEmpty() && titleKey.isEmpty())
                ? issueRepository.findAll(pageable).map(mapper::toIssueDTO)
                : issueRepository.findAllByPublisherKeyAndTitleKey(
                    publisherKey.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    titleKey.orElseThrow(() -> new TitleKeyNotFoundException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    variant,
                    pageable).map(mapper::toIssueDTO);
    }

    @Override
    public Optional<IssueDTO> getIssueByPublisherKeyAndTitleKeyAndIssueKey(Optional<String> publisherKey,
                                                              Optional<String> titleKey,
                                                              String issueKey) throws PublisherKeyNotFoundException, TitleKeyNotFoundException{
        return (issueRepository.existsById(issueKey))
                ? issueRepository.findById(issueKey).map(mapper::toIssueDTO)
                : issueRepository.findOneByPublisherKeyAndTitleKeyAndIssueKey(
                    publisherKey.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    titleKey.orElseThrow(() -> new TitleKeyNotFoundException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    issueKey).map(mapper::toIssueDTO);
    }

    @Override
    public Page<IssueDTO> getVariantIssuesByPublisherKeyAndTitleKeyAndIssueKey(Optional<String> publisherKey,
                                                                            Optional<String> titleKey,
                                                                            String issueKey,
                                                                            Pageable pageable) {
        return (issueRepository.existsById(issueKey))
                ? issueRepository.findAllByVariantOfAndVariantIsTrue(new ObjectId(issueKey), pageable).map(mapper::toIssueDTO)
                : issueRepository.findAllVariantsByPublisherKeyAndTitleKeyAndIssueKey(
                    publisherKey.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    titleKey.orElseThrow(() -> new TitleKeyNotFoundException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    issueKey,
                    pageable).map(mapper::toIssueDTO);
    }
}
