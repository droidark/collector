package com.comixtorm.collector.exception;

import com.comixtorm.collector.domain.model.exception.NoContentException;
import com.comixtorm.collector.domain.model.exception.PublisherKeyNotFoundException;
import com.comixtorm.collector.domain.model.exception.TitleKeyNotFoundException;
import com.comixtorm.collector.exception.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandle {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .detail(request.getDescription(false))
                .build();
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }

    @ExceptionHandler(NoContentException.class)
    public final ResponseEntity<?> handleNoContentException(Exception ex, WebRequest request) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(PublisherKeyNotFoundException.class)
    public final ResponseEntity<?> handlePublisherKeyNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .detail(request.getDescription(false))
                .build();
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(TitleKeyNotFoundException.class)
    public final ResponseEntity<?> handleTitleKeyNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .detail(request.getDescription(false))
                .build();
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
}
