package net.comicorp.collector.service;

import java.util.function.BooleanSupplier;

public interface KeyValidator {
    void validateKey(String key, BooleanSupplier existsFunction, String errorMessage);
}
