package xyz.krakenkat.collector.domain.repository;

import xyz.krakenkat.collector.domain.model.Title;
import xyz.krakenkat.collector.domain.repository.custom.CustomTitleRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TitleRepository extends MongoRepository<Title, String>, CustomTitleRepository {
    Page<Title> findAll(Pageable pageable);
    Page<Title> findAllByPublisherKey(String key, Pageable pageable);
    Page<Title> findAllByPublisher(ObjectId publisher, Pageable pageable);
    Page<Title> findAllByUsername(String username, Pageable pageable);
    Optional<Title> findById(ObjectId id);
    boolean existsByKey(String key);
    Optional<Title> findByPublisherKeyAndTitleKey(String publisherKey, String titleKey);
}
