package xyz.krakenkat.collector.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.krakenkat.collector.exception.FieldNotValidException;
import xyz.krakenkat.collector.exception.PublisherKeyNotFoundException;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.TitleKeyNotFoundException;

import java.util.Optional;

public interface TitleService {
    Page<IssueDTO> getAllIssuesByTitleKeyAndPublisherKey(String titleKey, String publisherKey, String variant, Pageable pageable);
    Page<TitleDTO> getTitlesByKey(String key, Pageable pageable);
    Optional<TitleDTO> getTitleByTitleKeyAndPublisherKey(String titleKey, String publisherKey) throws FieldNotValidException;
}
