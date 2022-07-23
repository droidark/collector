package com.comixtorm.collector.service.impl;

import com.comixtorm.collector.domain.model.Issue;
import com.comixtorm.collector.domain.model.exception.PublisherKeyNotFoundException;
import com.comixtorm.collector.domain.model.exception.TitleKeyNotFoundException;
import com.comixtorm.collector.domain.repository.IssueRepository;
import com.comixtorm.collector.service.IssueService;
import com.comixtorm.collector.util.Constants;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("issueService")
@AllArgsConstructor
public class IssueServiceImpl implements IssueService {

    private IssueRepository issueRepository;

    @Override
    public Page<Issue> getIssuesByPublisherKeyAndTitleKey(Optional<String> publisherKey,
                                                          Optional<String> titleKey,
                                                          boolean variant,
                                                          Pageable pageable) throws PublisherKeyNotFoundException, TitleKeyNotFoundException {
        return (!publisherKey.isPresent() & !titleKey.isPresent())
                ? issueRepository.findAll(pageable)
                : issueRepository.findAllByPublisherKeyAndTitleKey(
                    publisherKey.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    titleKey.orElseThrow(() -> new TitleKeyNotFoundException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    variant,
                    pageable);
    }

    @Override
    public Optional<Issue> getIssueByPublisherKeyAndTitleKeyAndIssueKey(Optional<String> publisherKey,
                                                              Optional<String> titleKey,
                                                              String issueKey) throws PublisherKeyNotFoundException, TitleKeyNotFoundException{
        return (issueRepository.existsById(issueKey))
                ? issueRepository.findById(issueKey)
                : issueRepository.findOneByPublisherKeyAndTitleKeyAndIssueKey(
                    publisherKey.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    titleKey.orElseThrow(() -> new TitleKeyNotFoundException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    issueKey);
    }

    @Override
    public Page<Issue> getVariantIssuesByPublisherKeyAndTitleKeyAndIssueKey(Optional<String> publisherKey,
                                                                            Optional<String> titleKey,
                                                                            String issueKey,
                                                                            Pageable pageable) {
        return (issueRepository.existsById(issueKey))
                ? issueRepository.findAllByVariantOfAndVariantIsTrue(new ObjectId(issueKey), pageable)
                : issueRepository.findAllVariantsByPublisherKeyAndTitleKeyAndIssueKey(
                    publisherKey.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    titleKey.orElseThrow(() -> new TitleKeyNotFoundException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE)),
                    issueKey,
                    pageable);
    }
}
