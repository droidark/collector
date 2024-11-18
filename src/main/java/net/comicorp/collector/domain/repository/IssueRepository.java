package net.comicorp.collector.domain.repository;

import net.comicorp.collector.domain.model.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

    @Query("SELECT i FROM Issue i " +
            "JOIN i.title t " +
            "JOIN t.publisher p " +
            "WHERE t.key = :titleKey " +
            "AND p.key = :publisherKey " +
            "AND (:variant IS NULL OR i.variant = :variant)")
    Page<Issue> findByPublisherKeyAndTitleKey(@Param("publisherKey") String publisherKey,
                                              @Param("titleKey") String titleKey,
                                              @Param("variant") Boolean variant,
                                              Pageable pageable);

    @Query("SELECT i FROM Issue i " +
            "JOIN i.title t " +
            "JOIN t.publisher p " +
            "WHERE t.key = :titleKey " +
            "AND p.key = :publisherKey " +
            "AND i.key = :key " +
            "AND (:variant IS NULL OR i.variant = :variant)")
    Page<Issue> findByKeyAndPublisherKeyAndTitleKey(
            @Param("publisherKey") String publisherKey,
            @Param("titleKey") String titleKey,
            @Param("key") String key,
            @Param("variant") Boolean variant,
            Pageable pageable);

    boolean existsByKey(String key);
}
