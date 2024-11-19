package net.comicorp.collector.exception;

import lombok.Getter;

@Getter
public class FieldNotValidException extends RuntimeException {
    private final String rejectedValue;
    private final String field;

    public FieldNotValidException(String message, String rejectedValue, String field) {
        super(message);
        this.rejectedValue = rejectedValue;
        this.field = field;
    }
}
