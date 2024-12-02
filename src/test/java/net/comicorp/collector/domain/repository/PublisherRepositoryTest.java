package net.comicorp.collector.domain.repository;

import net.comicorp.collector.domain.model.Publisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static net.comicorp.collector.util.PublisherUtilities.buildPublisher;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository repository;

    @Test
    @DisplayName("Should return all publishers")
    void shouldReturnAllPublishers() {
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
    void shouldAddPublisher() {
        // Given
        Publisher publisher = buildPublisher();

        // When
        Publisher savedPublisher = repository.save(publisher);

        // Then
        assertEquals(publisher, savedPublisher);
    }

    @Test
    @DisplayName("Should remove publisher")
    void shouldRemovePublisher() {
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
    void shouldReturnPublisherByKey() {
        // Given
        String key = "ecolife";

        // When
        Optional<Publisher> publisher = repository.findByKey(key);

        // Then
        assertTrue(publisher.isPresent());
        assertEquals(key, publisher.get().getKey());
    }
}