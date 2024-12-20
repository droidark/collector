package net.comicorp.collector.domain.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import net.comicorp.collector.domain.model.Publisher;
import net.comicorp.collector.domain.model.Title;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static net.comicorp.collector.util.TitleUtilities.buildTitle;

@DataJpaTest
class TitleRepositoryTest {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    @DisplayName("Should return all titles")
    void shouldReturnAllTitles() {
        // Given
        int expected = 25, pageNumber = 0, pageSize = 10;

        // When
        Page<Title> titles = titleRepository.findAll(PageRequest.of(pageNumber, pageSize));

        // Then
        assertTrue(titles.hasContent());
        assertEquals(expected, titles.getTotalElements());
    }

    @Test
    @DisplayName("Should add a title")
    void shouldAddTitle() {
        // Given
        Publisher publisher = publisherRepository.findById(1L).orElseGet(Publisher::new);
        Title title = buildTitle(publisher);

        // When
        Title savedTitle = titleRepository.save(buildTitle(publisher));

        // Then
        assertEquals(title.getKey(), savedTitle.getKey());
    }

    @Test
    @DisplayName("Should remove a title")
    void shouldRemoveTitle() {
        // Given
        long id = 1L;
        Optional<Title> title = titleRepository.findById(id);

        // When
        titleRepository.delete(title.orElseThrow());

        // Then
        assertFalse(titleRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Should return all titles by publisher key")
    void shouldReturnAllTitlesByPublisherKey() {
        // Given
        long id = 1L;
        String key = publisherRepository.findById(id).get().getKey();
        int expected = 5, pageNumber = 0, pageSize = 10;

        // When
        Page<Title> titles = titleRepository.findByPublisherKey(key, PageRequest.of(pageNumber, pageSize));

        // Then
        assertTrue(titles.hasContent());
        assertEquals(expected, titles.getTotalElements());
    }
}