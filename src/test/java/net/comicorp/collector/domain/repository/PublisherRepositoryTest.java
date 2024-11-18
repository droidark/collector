package net.comicorp.collector.domain.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import net.comicorp.collector.domain.model.Publisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static net.comicorp.collector.util.PublisherUtilities.buildPublisher;

@DataJpaTest
class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository repository;

    @Test
    @DisplayName("Should return all publishers")
    void should_return_all_publishers() {
        // Given
        int expected = 5, pageNumber = 0, pageSize = 10;

        // When
        Page<Publisher> publishers = repository.findAll(PageRequest.of(pageNumber, pageSize));

        // Then
        assertTrue(publishers.hasContent());
        assertEquals(expected, publishers.getTotalElements());
    }

    @Test
    @DisplayName("Should add a publisher")
    void should_add_a_publisher() {
        // Given
        Publisher publisher = buildPublisher();

        // When
        Publisher savedPublisher = repository.save(publisher);

        // Then
        assertEquals(publisher, savedPublisher);
    }

    @Test
    @DisplayName("Should remove publisher")
    void should_remove_publisher() {
        // Given
        long id = 1L;
        Optional<Publisher> publisher = repository.findById(id);

        // When
        repository.delete(publisher.orElseThrow());

        // Then
        assertFalse(repository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Should return publisher by key")
    void should_return_publisher_by_key() {
        // Given
        String key = "ecolife";

        // When
        Optional<Publisher> publisher = repository.findByKey(key);

        // Then
        assertTrue(publisher.isPresent());
        assertEquals(key, publisher.get().getKey());
    }
}