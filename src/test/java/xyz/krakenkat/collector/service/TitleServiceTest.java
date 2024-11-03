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
import xyz.krakenkat.collector.domain.model.Title;
import xyz.krakenkat.collector.domain.repository.PublisherRepository;
import xyz.krakenkat.collector.domain.repository.TitleRepository;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.FieldNotValidException;
import xyz.krakenkat.collector.service.impl.TitleServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static xyz.krakenkat.collector.util.TestUtilities.generateRandomKey;
import static xyz.krakenkat.collector.util.TitleUtilities.buildTitleDTOList;
import static xyz.krakenkat.collector.util.TitleUtilities.buildTitleList;

@ExtendWith(SpringExtension.class)
class TitleServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private TitleRepository titleRepository;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private TitleServiceImpl titleService;

    @Test
    @DisplayName("Should return a title by id")
    void should_return_a_title_by_id() {
        // Given
        Long id = 1L;
        String expected = "future-tech-monthly";

        // When
        when(titleRepository.findById(anyLong())).thenReturn(Optional.of(buildTitleList().getFirst()));
        when(mapperService.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().getFirst());

        TitleDTO title = titleService.getTitleById(id);

        // Then
        assertEquals(expected, title.getKey());
    }

    @Test
    @DisplayName("Should return titles by publisher key when publisher key exists")
    void should_return_titles_by_publisher_key_when_publisher_key_exists() {
        // Given
        String key = generateRandomKey(5);
        int pageNumber = 0, pageSize = 10, expected = 5;

        // When
        when(publisherRepository.existsByKey(anyString())).thenReturn(Boolean.TRUE);
        when(titleRepository.findByPublisherKey(key, PageRequest.of(pageNumber, pageSize))).thenReturn(new PageImpl<>(buildTitleList()));
        when(mapperService.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().getFirst());
        when(mapperService.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().get(1));
        when(mapperService.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().get(2));
        when(mapperService.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().get(3));
        when(mapperService.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().getLast());

        Page<TitleDTO> result = titleService.getAllTitlesByPublisherKey(key, PageRequest.of(pageNumber, pageSize));

        // Then
        assertEquals(expected, result.getTotalElements());
    }

    @Test
    @DisplayName("Should return titles by publisher key when publisher key doesn't  exists")
    void should_return_titles_by_publisher_key_when_publisher_key_doesnt_exists() {
        // Given
        int pageNumber = 0, pageSize = 10;
        String key = generateRandomKey(5);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        // When
        when(publisherRepository.existsByKey(anyString())).thenReturn(Boolean.FALSE);

        // Then
        assertThrows(FieldNotValidException.class, () -> titleService.getAllTitlesByPublisherKey(key, pageRequest));
    }
}