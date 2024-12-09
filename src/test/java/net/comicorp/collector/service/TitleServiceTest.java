package net.comicorp.collector.service;

import net.comicorp.collector.component.KeyValidator;
import net.comicorp.collector.component.Mapper;
import net.comicorp.collector.domain.model.Issue;
import net.comicorp.collector.domain.model.Title;
import net.comicorp.collector.domain.repository.IssueRepository;
import net.comicorp.collector.domain.repository.PublisherRepository;
import net.comicorp.collector.domain.repository.TitleRepository;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.exception.NoContentException;
import net.comicorp.collector.service.impl.TitleServiceImpl;
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
import java.util.function.BooleanSupplier;

import static net.comicorp.collector.constant.Constants.PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE;
import static net.comicorp.collector.constant.Constants.TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE;
import static net.comicorp.collector.util.IssueUtilities.buildIssueDTOList;
import static net.comicorp.collector.util.IssueUtilities.buildIssueList;
import static net.comicorp.collector.util.PublisherUtilities.buildPublisher;
import static net.comicorp.collector.util.TestUtilities.generateRandomKey;
import static net.comicorp.collector.util.TitleUtilities.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TitleServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private TitleRepository titleRepository;

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private KeyValidator keyValidator;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private TitleServiceImpl titleService;

    /*  +-----------------------------------+
        |   getAllTitlesByPublisherKey()    |
        +-----------------------------------+   */
    @Test
    @DisplayName("Should return titles by publisher key when publisher key exists")
    void shouldReturnTitlesByPublisherKeyWhenPublisherKeyExists() {
        // Given
        String key = generateRandomKey(5);
        int pageNumber = 0, pageSize = 10, expected = 5;

        // When
        // Mock KeyValidator accepts the key
        doNothing()
                .when(keyValidator).validateKey(eq(key), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        when(publisherRepository.existsByKey(anyString())).thenReturn(Boolean.TRUE);
        when(titleRepository.findByPublisherKey(key, PageRequest.of(pageNumber, pageSize))).thenReturn(new PageImpl<>(buildTitleList()));
        when(mapper.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().getFirst());
        when(mapper.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().get(1));
        when(mapper.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().get(2));
        when(mapper.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().get(3));
        when(mapper.toTitleDTO(any(Title.class))).thenReturn(buildTitleDTOList().getLast());

        Page<TitleDTO> result = titleService.getAllTitlesByPublisherKey(key, PageRequest.of(pageNumber, pageSize));

        // Then
        assertEquals(expected, result.getTotalElements());
    }

    @Test
    @DisplayName("Should throw FieldNotValidException when publisher key isn't valid")
    void shouldThrowFieldNotValidExceptionWhenPublisherKeyIsNotValid() {
        // Given
        String key = "key", invalidKey = generateRandomKey(5);
        PageRequest pageRequest = PageRequest.of(0, 10);

        // Mock KeyValidator to throw the exception
        doThrow(new FieldNotValidException(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, invalidKey, key))
                .when(keyValidator)
                .validateKey(eq(invalidKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        // When & Then
        FieldNotValidException exception = assertThrows(FieldNotValidException.class, () -> titleService.getAllTitlesByPublisherKey(invalidKey, pageRequest));

        // Verify the exception message and fields
        assertEquals(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
        assertEquals(invalidKey, exception.getRejectedValue());
        assertEquals(key, exception.getField());

        // Verify that validateKey was called
        verify(keyValidator).validateKey(eq(invalidKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        // Ensure titleRepository is not called
        verifyNoInteractions(titleRepository);
    }

    /*  +-----------------------------------+
        |   getTitleByKeyAndPublisherKey()  |
        +-----------------------------------+   */
    @Test
    @DisplayName("Should return a title given publisher key and title key exists")
    void shouldReturnATitleGivenPublisherKeyAndTitleKeyExists() {
        // Given
        String publisherKey = generateRandomKey(5);
        String key = generateRandomKey(5);

        Optional<Title> title = Optional.of(buildTitle(buildPublisher()));

        // When
        // Mock KeyValidator accepts the key
        doNothing()
                .when(keyValidator).validateKey(eq(publisherKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        doNothing()
                .when(keyValidator).validateKey(eq(key), any(BooleanSupplier.class), eq(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        when(titleRepository.findByKeyAndPublisherKey(key, publisherKey)).thenReturn(title);
        when(mapper.toTitleDTO(title.get())).thenReturn(buildTitleDTO());

        TitleDTO result = titleService.getTitleByKeyAndPublisherKey(key, publisherKey);

        // Then
        assertEquals(title.get().getKey(), result.getKey());
    }

    @Test
    @DisplayName("Should throw NoContentException given publisher key and title key")
    void shouldThrowNoContentExceptionGivenPublisherKeyAndTitleKey() {
        // Given
        String publisherKey = generateRandomKey(5);
        String key = generateRandomKey(5);

        Optional<Title> title = Optional.of(buildTitle(buildPublisher()));

        // When
        // Mock KeyValidator accepts the key
        doNothing()
                .when(keyValidator).validateKey(eq(publisherKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        doNothing()
                .when(keyValidator).validateKey(eq(key), any(BooleanSupplier.class), eq(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        when(titleRepository.findByKeyAndPublisherKey(key, publisherKey)).thenReturn(Optional.empty());
        when(mapper.toTitleDTO(title.get())).thenThrow(NoContentException.class);

        // Then
        assertThrows(NoContentException.class, () -> titleService.getTitleByKeyAndPublisherKey(key, publisherKey));
    }

    @Test
    @DisplayName("Should throw NoContentException given no valid publisher key and title key")
    void shouldThrowFieldNotValidExceptionGivenNoValidPublisherKeyAndTitleKey() {
        // Given
        String field = "publisherKey", invalidPublisherKey = generateRandomKey(5), titleKey = generateRandomKey(5);

        // When
        // Mock KeyValidator accepts the key
        // Mock KeyValidator to throw the exception
        doThrow(new FieldNotValidException(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, invalidPublisherKey, field))
                .when(keyValidator)
                .validateKey(eq(invalidPublisherKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        // When & Then
        FieldNotValidException exception = assertThrows(FieldNotValidException.class, () -> titleService.getTitleByKeyAndPublisherKey(titleKey, invalidPublisherKey));

        // Verify the exception message and fields
        assertEquals(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
        assertEquals(invalidPublisherKey, exception.getRejectedValue());
        assertEquals(field, exception.getField());

        // Verify that validateKey was called
        verify(keyValidator).validateKey(eq(invalidPublisherKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        // Ensure titleRepository is not called
        verifyNoInteractions(titleRepository);
    }

    @Test
    @DisplayName("Should throw NoContentException given publisher key and no valid title key")
    void shouldThrowFieldNotValidExceptionGivenPublisherKeyAndNoValidTitleKey() {
        // Given
        String field = "titleKey", publisherKey = generateRandomKey(5), invalidTitleKey = generateRandomKey(5);

        // When
        // Mock KeyValidator accepts the key
        // Mock KeyValidator to throw the exception
        doThrow(new FieldNotValidException(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, invalidTitleKey, field))
                .when(keyValidator)
                .validateKey(eq(invalidTitleKey), any(BooleanSupplier.class), eq(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        // When & Then
        FieldNotValidException exception = assertThrows(FieldNotValidException.class, () -> titleService.getTitleByKeyAndPublisherKey(invalidTitleKey, publisherKey));

        // Verify the exception message and fields
        assertEquals(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
        assertEquals(invalidTitleKey, exception.getRejectedValue());
        assertEquals(field, exception.getField());

        // Verify that validateKey was called
        verify(keyValidator).validateKey(eq(invalidTitleKey), any(BooleanSupplier.class), eq(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        // Ensure titleRepository is not called
        verifyNoInteractions(titleRepository);
    }

    /*  +-------------------------------------------+
        |   getAllIssuesByPublisherKeyAndTitleKey() |
        +-------------------------------------------+   */

    @Test
    @DisplayName("Should return all issues given a valid publisher key and title key")
    void shouldReturnAllIssuesGivenValidPublisherKeyAndTitleKey() {
        // Given
        int pageNumber = 0, pageSize = 10, expected = 10;
        String publisherKey = generateRandomKey(5);
        String titleKey = generateRandomKey(5);

        // When
        // Mock KeyValidator accepts the key
        doNothing()
                .when(keyValidator).validateKey(eq(publisherKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        doNothing()
                .when(keyValidator).validateKey(eq(titleKey), any(BooleanSupplier.class), eq(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));


        when(issueRepository.findByPublisherKeyAndTitleKey(publisherKey, titleKey, Boolean.FALSE, PageRequest.of(pageNumber, pageSize))).thenReturn(new PageImpl<>(buildIssueList(Boolean.FALSE)));
        when(mapper.toIssueDTO(any(Issue.class))).thenReturn(buildIssueDTOList(Boolean.FALSE).getFirst());
        when(mapper.toIssueDTO(any(Issue.class))).thenReturn(buildIssueDTOList(Boolean.FALSE).get(1));
        when(mapper.toIssueDTO(any(Issue.class))).thenReturn(buildIssueDTOList(Boolean.FALSE).get(2));
        when(mapper.toIssueDTO(any(Issue.class))).thenReturn(buildIssueDTOList(Boolean.FALSE).get(3));
        when(mapper.toIssueDTO(any(Issue.class))).thenReturn(buildIssueDTOList(Boolean.FALSE).get(4));
        when(mapper.toIssueDTO(any(Issue.class))).thenReturn(buildIssueDTOList(Boolean.FALSE).get(5));
        when(mapper.toIssueDTO(any(Issue.class))).thenReturn(buildIssueDTOList(Boolean.FALSE).get(6));
        when(mapper.toIssueDTO(any(Issue.class))).thenReturn(buildIssueDTOList(Boolean.FALSE).get(7));
        when(mapper.toIssueDTO(any(Issue.class))).thenReturn(buildIssueDTOList(Boolean.FALSE).get(8));
        when(mapper.toIssueDTO(any(Issue.class))).thenReturn(buildIssueDTOList(Boolean.FALSE).getLast());

        Page<IssueDTO> result = titleService.getAllIssuesByPublisherKeyAndTitleKey(publisherKey, titleKey, Boolean.FALSE, PageRequest.of(pageNumber, pageSize));

        // Then
        assertEquals(expected, result.getTotalElements());
    }

    @Test
    @DisplayName("Should throw NoContentException given no valid publisher key and title key")
    void shouldThrowFieldNotValidExceptionGivenNoValidPublisherKeyAndTitleKeyWhenCallGetAllIssuesByPublisherKeyAndTitleKey() {
        // Given
        int pageNumber = 0, pageSize = 10;
        String invalidPublisherKey = generateRandomKey(5);
        String titleKey = generateRandomKey(5);
        String field = "publisherKey";

        // When
        // Mock KeyValidator accepts the key
        // Mock KeyValidator to throw the exception
        doThrow(new FieldNotValidException(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, invalidPublisherKey, field))
                .when(keyValidator)
                .validateKey(eq(invalidPublisherKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        // When & Then
        FieldNotValidException exception = assertThrows(FieldNotValidException.class,
                () -> callGetAllIssuesWithInvalidKey(invalidPublisherKey, titleKey, pageNumber, pageSize)
        );

        // Verify the exception message and fields
        assertEquals(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
        assertEquals(invalidPublisherKey, exception.getRejectedValue());
        assertEquals(field, exception.getField());

        // Verify that validateKey was called
        verify(keyValidator).validateKey(eq(invalidPublisherKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        // Ensure titleRepository is not called
        verifyNoInteractions(issueRepository);
    }

    @Test
    @DisplayName("Should throw NoContentException given no valid publisher key and title key")
    void shouldThrowFieldNotValidExceptionGivenPublisherKeyAndNoValidTitleKeyWhenCallGetAllIssuesByPublisherKeyAndTitleKey() {
        // Given
        int pageNumber = 0, pageSize = 10;
        String publisherKey = generateRandomKey(5);
        String invalidTitleKey = generateRandomKey(5);
        String field = "titleKey";

        // When
        // Mock KeyValidator accepts the key
        // Mock KeyValidator to throw the exception
        doThrow(new FieldNotValidException(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, invalidTitleKey, field))
                .when(keyValidator)
                .validateKey(eq(invalidTitleKey), any(BooleanSupplier.class), eq(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        // When & Then
        FieldNotValidException exception = assertThrows(FieldNotValidException.class,
                () -> callGetAllIssuesWithInvalidKey(publisherKey, invalidTitleKey, pageNumber, pageSize)
        );

        // Verify the exception message and fields
        assertEquals(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
        assertEquals(invalidTitleKey, exception.getRejectedValue());
        assertEquals(field, exception.getField());

        // Verify that validateKey was called
        verify(keyValidator).validateKey(eq(invalidTitleKey), any(BooleanSupplier.class), eq(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        // Ensure titleRepository is not called
        verifyNoInteractions(issueRepository);
    }

    private void callGetAllIssuesWithInvalidKey(String publisher, String title, int pageNumber, int pageSize) {
        titleService.getAllIssuesByPublisherKeyAndTitleKey(
                publisher,
                title,
                Boolean.FALSE,
                PageRequest.of(pageNumber, pageSize)
        );
    }
}