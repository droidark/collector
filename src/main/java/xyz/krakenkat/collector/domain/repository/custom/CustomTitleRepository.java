package xyz.krakenkat.collector.domain.repository.custom;

import xyz.krakenkat.collector.domain.model.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomTitleRepository {
    Page<Title> findAllByPublisherKey(String publisherKey, Pageable pageable);

    Page<Title> findAllByUsername(String username, Pageable pageable);

    Optional<Title> findByPublisherKeyAndTitleKey(String publisherKey, String titleKey);
}
