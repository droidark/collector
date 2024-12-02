package net.comicorp.collector.service;

import net.comicorp.collector.domain.model.Issue;
import net.comicorp.collector.domain.repository.IssueRepository;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.service.impl.IssueServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.function.BooleanSupplier;

import static net.comicorp.collector.constant.Constants.*;
import static net.comicorp.collector.util.IssueUtilities.buildSingleIssue;
import static net.comicorp.collector.util.IssueUtilities.buildSingleIssueDTO;
import static net.comicorp.collector.util.TestUtilities.generateRandomKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private KeyValidator keyValidator;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private IssueServiceImpl issueService;

    /*  +-------------------------------------------+
        |   getByKeyAndPublisherKeyAndTitleKey()    |
        +-------------------------------------------+   */

    @Test
    @DisplayName("Should return titles by publisher key when publisher key exists")
    void shouldReturnIssueByPublisherKeyAndTitleKey() {
        // Given
        int expected = 1;
        String publisherKey = generateRandomKey(5);
        String titleKey = generateRandomKey(5);
        String issueKey = generateRandomKey(5);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Issue> issues = new PageImpl<>(buildSingleIssue(Boolean.TRUE));


        // When
        doNothing().when(keyValidator).validateKey(eq(publisherKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        doNothing().when(keyValidator).validateKey(eq(titleKey), any(BooleanSupplier.class), eq(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        doNothing().when(keyValidator).validateKey(eq(issueKey), any(BooleanSupplier.class), eq(ISSUE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        when(issueRepository.findByKeyAndPublisherKeyAndTitleKey(publisherKey, titleKey, issueKey, Boolean.TRUE, pageable)).thenReturn(issues);

        when(mapperService.toIssueDTO(any(Issue.class))).thenReturn(buildSingleIssueDTO(Boolean.TRUE).getFirst());

        Page<IssueDTO> result = issueService.getByKeyAndPublisherKeyAndTitleKey(publisherKey, titleKey, issueKey, Boolean.TRUE, pageable);

        // Then
        assertEquals(expected, result.getTotalElements());
    }

    @Test
    @DisplayName("Should throw FieldNotValidException given no valid publisher key")
    void shouldThrowFieldNotValidExceptionGivenNoValidPublisherKey() {
        // Given
        String invalidPublisherKey = generateRandomKey(5);
        String titleKey = generateRandomKey(5);
        String issueKey = generateRandomKey(5);
        String field = "publisher";
        Pageable pageable = PageRequest.of(0, 10);

        // When
        doThrow(new FieldNotValidException(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, invalidPublisherKey, field))
                .when(keyValidator)
                .validateKey(eq(invalidPublisherKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        FieldNotValidException exception = assertThrows(FieldNotValidException.class,
                () -> issueService.getByKeyAndPublisherKeyAndTitleKey(invalidPublisherKey, titleKey, issueKey, Boolean.FALSE, pageable));

        // Then
        assertEquals(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
        assertEquals(invalidPublisherKey, exception.getRejectedValue());
        assertEquals(field, exception.getField());

        verify(keyValidator).validateKey(eq(invalidPublisherKey), any(BooleanSupplier.class), eq(PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        verifyNoInteractions(issueRepository);
    }

    @Test
    @DisplayName("Should throw FieldNotValidException given no valid publisher key")
    void shouldThrowFieldNotValidExceptionGivenNoValidTitleKey() {
        // Given
        String publisherKey = generateRandomKey(5);
        String invalidTitleKey = generateRandomKey(5);
        String issueKey = generateRandomKey(5);
        String field = "title";
        Pageable pageable = PageRequest.of(0, 10);

        // When
        doThrow(new FieldNotValidException(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, invalidTitleKey, field))
                .when(keyValidator)
                .validateKey(eq(invalidTitleKey), any(BooleanSupplier.class), eq(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        FieldNotValidException exception = assertThrows(FieldNotValidException.class,
                () -> issueService.getByKeyAndPublisherKeyAndTitleKey(publisherKey, invalidTitleKey, issueKey, Boolean.FALSE, pageable));

        // Then
        assertEquals(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
        assertEquals(invalidTitleKey, exception.getRejectedValue());
        assertEquals(field, exception.getField());

        verify(keyValidator).validateKey(eq(invalidTitleKey), any(BooleanSupplier.class), eq(TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        verifyNoInteractions(issueRepository);
    }

    @Test
    @DisplayName("Should throw FieldNotValidException given no valid issue key")
    void shouldThrowFieldNotValidExceptionGivenNoValidIssueKey() {
        // Given
        String publisherKey = generateRandomKey(5);
        String titleKey = generateRandomKey(5);
        String invalidIssueKey = generateRandomKey(5);
        String field = "issue";
        Pageable pageable = PageRequest.of(0, 10);

        // When
        doThrow(new FieldNotValidException(ISSUE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, invalidIssueKey, field))
                .when(keyValidator)
                .validateKey(eq(invalidIssueKey), any(BooleanSupplier.class), eq(ISSUE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        FieldNotValidException exception = assertThrows(FieldNotValidException.class,
                () -> issueService.getByKeyAndPublisherKeyAndTitleKey(publisherKey, titleKey, invalidIssueKey, Boolean.FALSE, pageable));

        // Then
        assertEquals(ISSUE_KEY_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
        assertEquals(invalidIssueKey, exception.getRejectedValue());
        assertEquals(field, exception.getField());

        verify(keyValidator).validateKey(eq(invalidIssueKey), any(BooleanSupplier.class), eq(ISSUE_KEY_NOT_FOUND_EXCEPTION_MESSAGE));

        verifyNoInteractions(issueRepository);
    }
}