package xyz.krakenkat.collector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TitleKeyNotFoundException extends RuntimeException {
    public TitleKeyNotFoundException(String message) {
        super(message);
    }
}
