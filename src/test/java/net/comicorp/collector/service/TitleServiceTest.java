package net.comicorp.collector.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import net.comicorp.collector.domain.model.Title;
import net.comicorp.collector.domain.repository.PublisherRepository;
import net.comicorp.collector.domain.repository.TitleRepository;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.service.impl.TitleServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static net.comicorp.collector.util.TestUtilities.generateRandomKey;
import static net.comicorp.collector.util.TitleUtilities.buildTitleDTOList;
import static net.comicorp.collector.util.TitleUtilities.buildTitleList;

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