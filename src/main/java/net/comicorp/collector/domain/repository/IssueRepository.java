package net.comicorp.collector.domain.repository;

import net.comicorp.collector.domain.model.Issue;
import net.comicorp.collector.dto.IssueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

    boolean existsByKey(String key);

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

    @Query("SELECT new net.comicorp.collector.dto.IssueDTO(" +
            "i.name, " +
            "i.key, " +
            "i.number, " +
            "i.cover, " +
            "i.pages, " +
            "i.printedPrice, " +
            "i.digitalPrice, " +
            "i.currency, " +
            "i.releaseDate, " +
            "i.shortReview, " +
            "i.event, " +
            "i.storyArch, " +
            "i.isbn, " +
            "i.barcode, " +
            "i.edition, " +
            "i.variant, " +
            "i.likesCounter, " +
            "i.dislikesCounter, " +
            "(CASE WHEN u.id IS NOT NULL THEN true ELSE false END)) " +
            "FROM Issue i " +
            "LEFT JOIN i.users u ON u.username = :username " +
            "JOIN i.title t " +
            "JOIN t.publisher p " +
            "WHERE t.key = :titleKey " +
            "AND p.key = :publisherKey " +
            "AND (:variant IS NULL OR i.variant = :variant) " +
            "AND (:collected IS NULL OR " +
            "     (:collected = true AND u.id IS NOT NULL) OR " +
            "     (:collected = false AND u.id IS NULL))")
    Page<IssueDTO> findIssuesWithDynamicFilters(
            @Param("username") String username,
            @Param("titleKey") String titleKey,
            @Param("publisherKey") String publisherKey,
            @Param("collected") Boolean collected,
            @Param("variant") Boolean variant,
            Pageable pageable);

    @Query(name = "Issue.findByUserAndTitleAndPublisher")
    Set<Issue> findByUserAndTitleAndPublisher(@Param("username") String username,
                                              @Param("titleKey") String titleLookupKey,
                                              @Param("publisherKey") String publisherLookupKey);

    @Query(name = "Issue.findByTitlePublisherAndKeys")
    Set<Issue> findByTitlePublisherAndKeys(@Param("titleKey") String titleKey,
                                           @Param("publisherKey") String publisherKey,
                                           @Param("keys") List<String> keys);
}
