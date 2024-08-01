package xyz.krakenkat.collector.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.krakenkat.collector.domain.model.Publisher;
import xyz.krakenkat.collector.domain.repository.PublisherRepository;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.impl.PublisherServiceImpl;
import xyz.krakenkat.collector.util.MapperService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static xyz.krakenkat.collector.util.TestUtilities.*;

@ExtendWith(SpringExtension.class)
class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @Test
    @DisplayName("Should return all publishers")
    void should_return_all_publishers() {
        // Given
        int expected = 1;

        // When
        when(publisherRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(buildPublishers()));
        when(mapperService.toPublisherDTO(any(Publisher.class))).thenReturn(buildPublisherDTO());
        Page<PublisherDTO> result = publisherService.getPublishers(PageRequest.of(0, 10));

        // Then
        assertEquals(expected, result.getTotalElements());
    }

    @Test
    @DisplayName("Should return publisher by key")
    void should_return_publisher_by_key() {
        // Given
        String key = "mangaline-mx";

        // When
        when(publisherRepository.findByKey(anyString())).thenReturn(Optional.of(buildPublisher()));
        when(mapperService.toPublisherDTO(any(Publisher.class))).thenReturn(buildPublisherDTO());
        PublisherDTO result = publisherService.getPublisherByKey(key);

        // Then
        assertEquals(key, result.getKey());
    }

    @Test()
    @DisplayName("should_return_publisher_by_key_throws_NoContentException")
    void should_return_publisher_by_key_throws_NoContentException() {
        // Given
        String key = "mangaline-mx";

        // When
        when(publisherRepository.findByKey(anyString())).thenReturn(Optional.empty());
        when(mapperService.toPublisherDTO(any(Publisher.class))).thenReturn(buildPublisherDTO());

        // Then
        assertThrows(NoContentException.class, () -> publisherService.getPublisherByKey(key));
    }
}