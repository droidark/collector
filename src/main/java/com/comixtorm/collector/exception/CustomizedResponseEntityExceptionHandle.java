package com.comixtorm.collector.exception;

import com.comixtorm.collector.domain.model.exception.NoContentException;
import com.comixtorm.collector.domain.model.exception.PublisherKeyNotFoundException;
import com.comixtorm.collector.domain.model.exception.TitleKeyNotFoundException;
import com.comixtorm.collector.exception.response.Detail;
import com.comixtorm.collector.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandle {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .error(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .timestamp(Date.from(Instant.now()))
                .detail(List.of(Detail
                        .builder()
                        .message(request.getDescription(false))
                        .build()))
                .build();
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }

    @ExceptionHandler(NoContentException.class)
    public final ResponseEntity<?> handleNoContentException(Exception ex, WebRequest request) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(Date.from(Instant.now()))
                .error(HttpStatus.BAD_REQUEST.toString())
                .detail(ex.getBindingResult()
                        .getFieldErrors()
                        .stream().map(error -> Detail
                                .builder()
                                .message(error.getDefaultMessage())
                                .rejectedValue(error.getRejectedValue().toString())
                                .field(error.getField())
                                .build()).collect(Collectors.toList()))
                .build();
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(PublisherKeyNotFoundException.class)
    public final ResponseEntity<?> handlePublisherKeyNotFoundException(Exception ex) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(Date.from(Instant.now()))
                .error(HttpStatus.BAD_REQUEST.toString())
                .detail(List.of(Detail
                        .builder()
                        .message(ex.getMessage())
                        .build()))
                .build();
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(TitleKeyNotFoundException.class)
    public final ResponseEntity<?> handleTitleKeyNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(Date.from(Instant.now()))
                .error(HttpStatus.BAD_REQUEST.toString())
                .detail(List.of(Detail
                        .builder()
                        .message(ex.getMessage())
                        .build()))
                .build();
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
}
