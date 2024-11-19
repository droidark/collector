package net.comicorp.collector.service.impl;

import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.service.KeyValidator;
import org.springframework.stereotype.Service;

import java.util.function.BooleanSupplier;

@Service
public class KeyValidatorImpl implements KeyValidator {

    @Override
    public void validateKey(String key, BooleanSupplier existsFunction, String errorMessage) {
        if (!existsFunction.getAsBoolean()) {
            throw new FieldNotValidException(errorMessage, key, "key");
        }
    }
}
