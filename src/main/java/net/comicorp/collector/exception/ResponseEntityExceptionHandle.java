package net.comicorp.collector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import net.comicorp.collector.exception.response.Detail;
import net.comicorp.collector.exception.response.ExceptionResponse;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
@RestController
public class ResponseEntityExceptionHandle {

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<ExceptionResponse> handleAllExceptions(WebRequest request) {
//        ExceptionResponse exceptionResponse = ExceptionResponse
//                .builder()
//                .error(HttpStatus.INTERNAL_SERVER_ERROR.toString())
//                .timestamp(Date.from(Instant.now()))
//                .detail(List.of(Detail
//                        .builder()
//                        .message(request.getDescription(false))
//                        .build()))
//                .build();
//        return ResponseEntity.internalServerError().body(exceptionResponse);
//    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionResponse> handleAccessDeniedException(WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder().error(HttpStatus.FORBIDDEN.toString())
                .timestamp(Date.from(Instant.now()))
                .detail(List.of(Detail
                        .builder()
                        .message(request.getDescription(false))
                        .build()))
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }

    @ExceptionHandler(JwtException.class)
    public final ResponseEntity<ExceptionResponse> handleJwtException(JwtException ex) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder().error(HttpStatus.BAD_REQUEST.toString())
                .timestamp(Date.from(Instant.now()))
                .detail(List.of(Detail
                        .builder()
                        .message(ex.getMessage())
                        .build()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(NoContentException.class)
    public final ResponseEntity<ExceptionResponse> handleNoContentException() {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public final ResponseEntity<ExceptionResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .timestamp(Date.from(Instant.now()))
                .error(HttpStatus.BAD_REQUEST.toString())
                .detail(List.of(Detail
                        .builder()
                        .message(ex.getMessage())
                        .rejectedValue("Missing value.")
                        .field(ex.getParameterName())
                        .build()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
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
                                .rejectedValue(Objects.requireNonNull(error.getRejectedValue()).toString())
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
}
