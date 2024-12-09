package net.comicorp.collector.component;

import net.comicorp.collector.exception.FieldNotValidException;
import org.springframework.stereotype.Service;

import java.util.function.BooleanSupplier;

@Service
public class KeyValidator {

    public void validateKey(String key, BooleanSupplier existsFunction, String errorMessage) {
        if (!existsFunction.getAsBoolean()) {
            throw new FieldNotValidException(errorMessage, key, "key");
        }
    }
}
