package xyz.krakenkat.collector.service.impl;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.model.exception.PublisherKeyNotFoundException;
import xyz.krakenkat.collector.domain.model.exception.TitleKeyNotFoundException;
import xyz.krakenkat.collector.domain.repository.IssueRepository;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.mapper.Convert;
import xyz.krakenkat.collector.service.IssueService;
import xyz.krakenkat.collector.util.Constants;

import java.util.Optional;

@Service("issueService")
@AllArgsConstructor
public class IssueServiceImpl implements IssueService {

    private IssueRepository issueRepository;

    @Override
    public Page<IssueDTO> getIssuesByPublisherKeyAndTitleKey(Optional<String> publisherKey,
                                                             Optional<String> titleKey,
                                                             boolean variant,
                                                             Pageable pageable) throws PublisherKeyNotFoundException, TitleKeyNotFoundException {
        return (!publisherKey.isPresent() && !titleKey.isPresent())
                ? issueRepository.findAll(pageable).map(Convert::toIssueDTO)
                : issueRepository.findAllByPublisherKeyAndTitleKey(
                    publisherKey.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    titleKey.orElseThrow(() -> new TitleKeyNotFoundException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    variant,
                    pageable).map(Convert::toIssueDTO);
    }

    @Override
    public Optional<IssueDTO> getIssueByPublisherKeyAndTitleKeyAndIssueKey(Optional<String> publisherKey,
                                                              Optional<String> titleKey,
                                                              String issueKey) throws PublisherKeyNotFoundException, TitleKeyNotFoundException{
        return (issueRepository.existsById(issueKey))
                ? issueRepository.findById(issueKey).map(Convert::toIssueDTO)
                : issueRepository.findOneByPublisherKeyAndTitleKeyAndIssueKey(
                    publisherKey.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    titleKey.orElseThrow(() -> new TitleKeyNotFoundException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    issueKey).map(Convert::toIssueDTO);
    }

    @Override
    public Page<IssueDTO> getVariantIssuesByPublisherKeyAndTitleKeyAndIssueKey(Optional<String> publisherKey,
                                                                            Optional<String> titleKey,
                                                                            String issueKey,
                                                                            Pageable pageable) {
        return (issueRepository.existsById(issueKey))
                ? issueRepository.findAllByVariantOfAndVariantIsTrue(new ObjectId(issueKey), pageable).map(Convert::toIssueDTO)
                : issueRepository.findAllVariantsByPublisherKeyAndTitleKeyAndIssueKey(
                    publisherKey.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    titleKey.orElseThrow(() -> new TitleKeyNotFoundException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    issueKey,
                    pageable).map(Convert::toIssueDTO);
    }
}
