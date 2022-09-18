package xyz.krakenkat.collector.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.krakenkat.collector.exception.PublisherKeyNotFoundException;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.TitleDTO;

import java.util.Optional;

public interface TitleService {
    Page<TitleDTO> getAllTitles(Pageable pageable);
    Page<IssueDTO> getAllIssuesByTitle(ObjectId titleId, Pageable pageable);
    Page<TitleDTO> getTitlesByKey(String key, Pageable pageable);
    Optional<TitleDTO> getTitleByKey(String key, Optional<String> publisher) throws PublisherKeyNotFoundException;
}
