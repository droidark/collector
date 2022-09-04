package xyz.krakenkat.collector.exception;

import xyz.krakenkat.collector.domain.model.exception.FieldNotValidException;
import xyz.krakenkat.collector.domain.model.exception.NoContentException;
import xyz.krakenkat.collector.domain.model.exception.PublisherKeyNotFoundException;
import xyz.krakenkat.collector.domain.model.exception.TitleKeyNotFoundException;
import xyz.krakenkat.collector.exception.response.Detail;
import xyz.krakenkat.collector.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandle {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(WebRequest request) {
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
    public final ResponseEntity<ExceptionResponse> handleNoContentException() {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException ex) {
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
                                .build()).toList())
                .build();
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(FieldNotValidException.class)
    public final ResponseEntity<ExceptionResponse> handleFieldNotValidException(FieldNotValidException ex) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(Date.from(Instant.now()))
                .error(HttpStatus.BAD_REQUEST.toString())
                .detail(List.of(Detail
                        .builder()
                        .message(ex.getMessage())
                        .rejectedValue(ex.getRejectedValue())
                        .field(ex.getField())
                        .build()))
                .build();
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler({PublisherKeyNotFoundException.class,
            TitleKeyNotFoundException.class,
            UsernameNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleMissingArgumentKeyNotFoundException(Exception ex) {
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
