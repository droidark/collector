package net.comicorp.collector.service;

import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.service.impl.KeyValidatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class KeyValidatorTest {

    @InjectMocks
    private KeyValidatorImpl keyValidator;

    @Test
    void shouldThrowExceptionWhenKeyDoesNotExist() {
        // Arrange
        String key = "invalidKey";
        String errorMessage = "Key not found";
        BooleanSupplier existsFunction = mock(BooleanSupplier.class);
        when(existsFunction.getAsBoolean()).thenReturn(false);

        // Act & Assert
        FieldNotValidException exception = assertThrows(
                FieldNotValidException.class,
                () -> keyValidator.validateKey(key, existsFunction, errorMessage)
        );

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(key, exception.getRejectedValue());
        assertEquals("key", exception.getField());
    }

    @Test
    void shouldNotThrowExceptionWhenKeyExists() {
        // Arrange
        String key = "validKey";
        String errorMessage = "Key not found";
        BooleanSupplier existsFunction = mock(BooleanSupplier.class);
        when(existsFunction.getAsBoolean()).thenReturn(true);

        // Act & Assert
        assertDoesNotThrow(() -> keyValidator.validateKey(key, existsFunction, errorMessage));
    }
}