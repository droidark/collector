package net.comicorp.collector.domain.repository;

import net.comicorp.collector.domain.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByKey(String key);
    boolean existsByKey(String key);
}
