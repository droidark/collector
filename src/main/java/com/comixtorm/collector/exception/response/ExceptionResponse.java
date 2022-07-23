package com.comixtorm.collector.exception.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String detail;
}
