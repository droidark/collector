package net.comicorp.collector.service;

import net.comicorp.collector.domain.model.Publisher;
import net.comicorp.collector.domain.repository.PublisherRepository;
import net.comicorp.collector.dto.PublisherDTO;
import net.comicorp.collector.exception.NoContentException;
import net.comicorp.collector.service.impl.PublisherServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static net.comicorp.collector.util.PublisherUtilities.buildPublisherDTOList;
import static net.comicorp.collector.util.PublisherUtilities.buildPublisherList;
import static net.comicorp.collector.util.TestUtilities.generateRandomKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
        int pageNumber = 0, pageSize = 10, expected = 5;

        // When
        when(publisherRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(buildPublisherList()));
        when(mapperService.toPublisherDTO(any(Publisher.class))).thenReturn(buildPublisherDTOList().getFirst());
        when(mapperService.toPublisherDTO(any(Publisher.class))).thenReturn(buildPublisherDTOList().get(1));
        when(mapperService.toPublisherDTO(any(Publisher.class))).thenReturn(buildPublisherDTOList().get(2));
        when(mapperService.toPublisherDTO(any(Publisher.class))).thenReturn(buildPublisherDTOList().get(3));
        when(mapperService.toPublisherDTO(any(Publisher.class))).thenReturn(buildPublisherDTOList().getLast());

        Page<PublisherDTO> result = publisherService.getPublishers(PageRequest.of(pageNumber, pageSize));

        // Then
        assertEquals(expected, result.getTotalElements());
    }

    @Test
    @DisplayName("Should return publisher by key")
    void should_return_publisher_by_key() {
        // Given
        String key = "ecolife";

        // When
        when(publisherRepository.findByKey(anyString())).thenReturn(Optional.of(buildPublisherList().getLast()));
        when(mapperService.toPublisherDTO(any(Publisher.class))).thenReturn(buildPublisherDTOList().getLast());
        PublisherDTO result = publisherService.getPublisherByKey(key);

        // Then
        assertEquals(key, result.getKey());
    }

    @Test()
    @DisplayName("should_return_publisher_by_key_throws_NoContentException")
    void should_return_publisher_by_key_throws_NoContentException() {
        // Given
        String key = generateRandomKey(5);

        // When
        when(publisherRepository.findByKey(anyString())).thenReturn(Optional.empty());
        when(mapperService.toPublisherDTO(any(Publisher.class))).thenThrow(NoContentException.class);

        // Then
        assertThrows(NoContentException.class, () -> publisherService.getPublisherByKey(key));
    }
}