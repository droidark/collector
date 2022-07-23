package com.comixtorm.collector.domain.repository;

import com.comixtorm.collector.domain.model.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends MongoRepository<Publisher, String> {
    Page<Publisher> findAll(Pageable pageable);
    Optional<Publisher> findOneByKey(String key);
    boolean existsByKey(String key);
}
