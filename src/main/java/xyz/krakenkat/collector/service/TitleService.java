package xyz.krakenkat.collector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.FieldNotValidException;
import xyz.krakenkat.collector.exception.NoContentException;

public interface TitleService {

    Page<TitleDTO> getAllTitlesByPublisherKey(String publisherKey, Pageable pageable) throws NoContentException;

    TitleDTO getTitleByKeyAndPublisherKey(String key, String publisherKey) throws FieldNotValidException, NoContentException;
}
