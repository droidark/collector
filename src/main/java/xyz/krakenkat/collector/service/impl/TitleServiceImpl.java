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
    public Page<TitleDTO> getAllTitles(Pageable pageable) {
        return titleRepository.findAll(pageable).map(mapper::toTitleDTO);
    }

    @Override
    public Page<IssueDTO> getAllIssuesByTitle(ObjectId titleId, Pageable pageable) {
        return issueRepository.findAllByTitle(titleId, pageable).map(mapper::toIssueDTO);
    }

    @Override
    public Page<TitleDTO> getTitlesByKey(String key, Pageable pageable) {
        return publisherRepository.existsByKey(key)
                ? titleRepository.findAllByPublisherKey(key, pageable).map(mapper::toTitleDTO)
                : getTitlesById(key, pageable);
    }

    @Override
    public Optional<TitleDTO> getTitleByKey(String key, Optional<String> publisher) throws PublisherKeyNotFoundException {
        return titleRepository.existsByKey(key)
                ? titleRepository
                    .findByPublisherKeyAndTitleKey(publisher.orElseThrow(() -> new PublisherKeyNotFoundException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE)), key)
                    .map(mapper::toTitleDTO)
                : getTitleById(key);
    }

    private Optional<TitleDTO> getTitleById(String id) {
        if (ObjectId.isValid(id))
            return titleRepository.findById(new ObjectId(id)).map(mapper::toTitleDTO);
        throw new FieldNotValidException(Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, "titleId", id);
    }

    private Page<TitleDTO> getTitlesById(String id, Pageable pageable) {
        if (ObjectId.isValid(id))
            return titleRepository.findAllByPublisher(new ObjectId(id), pageable).map(mapper::toTitleDTO);
        throw new FieldNotValidException(Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, "publisherId", id);
    }
}
