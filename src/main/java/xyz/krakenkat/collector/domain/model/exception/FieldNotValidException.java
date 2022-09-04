package xyz.krakenkat.collector.domain.model.exception;


import lombok.Getter;

public class FieldNotValidException extends RuntimeException {
    @Getter
    private String rejectedValue;
    @Getter
    private String field;

    public FieldNotValidException(String message, String rejectedValue, String field) {
        super(message);
        this.rejectedValue = rejectedValue;
        this.field = field;
    }
}
