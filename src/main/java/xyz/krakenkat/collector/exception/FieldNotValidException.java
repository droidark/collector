package xyz.krakenkat.collector.exception;


import lombok.Getter;

public class FieldNotValidException extends RuntimeException {
    @Getter
    private final String rejectedValue;
    @Getter
    private final String field;

    public FieldNotValidException(String message, String rejectedValue, String field) {
        super(message);
        this.rejectedValue = rejectedValue;
        this.field = field;
    }
}
