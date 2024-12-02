package net.comicorp.collector.domain.repository;

import net.comicorp.collector.domain.model.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository repository;

    @Test
    @DisplayName("Should return all issues given publisher key and title key")
    void shouldReturnAllIssuesGivenPublisherKeyAndTitleKey() {
        // Given
        String publisher = "techworld";
        String title = "gadget-weekly";
        Pageable pageable = PageRequest.of(0, 10);
        int expected = 10;

        // When
        Page<Issue> issues = repository.findByPublisherKeyAndTitleKey(publisher, title, Boolean.FALSE, pageable);

        // Then
        assertTrue(issues.hasContent());
        assertEquals(expected, issues.getTotalElements());
    }

    @Test
    @DisplayName("Should return all issues given publisher key, title key and issue key")
    void shouldReturnAllIssuesGivenPublisherKeyAndTitleKeyAndIssueKey() {
        // Given
        String publisher = "techworld";
        String title = "gadget-weekly";
        String issue = "1";
        Pageable pageable = PageRequest.of(0, 10);
        int expected = 2;

        // When
        Page<Issue> issues = repository.findByKeyAndPublisherKeyAndTitleKey(publisher, title, issue, null, pageable);

        // Then
        assertTrue(issues.hasContent());
        assertEquals(expected, issues.getTotalElements());
    }
}