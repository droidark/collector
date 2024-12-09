package net.comicorp.collector.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import net.comicorp.collector.domain.model.Title;

import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

    Page <Title> findByPublisherKey(String publisherKey, Pageable pageable);
    Optional<Title> findByKeyAndPublisherKey(String key, String publisherKey);
    boolean existsByKey(String key);

    @Query("SELECT DISTINCT t FROM Title t JOIN t.issues i JOIN i.users u WHERE u.username = :username")
    Page<Title> findByUsername(String username, Pageable pageable);
}
