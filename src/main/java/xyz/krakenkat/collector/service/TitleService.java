package xyz.krakenkat.collector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.FieldNotValidException;
import xyz.krakenkat.collector.exception.NoContentException;

import java.util.Optional;

public interface TitleService {
    TitleDTO getTitleById(Long id) throws NoContentException;

    Page<TitleDTO> getAllTitlesByPublisherKey(String publisherKey, Pageable pageable) throws NoContentException;

    Optional<TitleDTO> getTitleByTitleKeyAndPublisherKey(String titleKey, String publisherKey) throws FieldNotValidException;
}
