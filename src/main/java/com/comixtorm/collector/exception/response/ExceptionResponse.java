package com.comixtorm.collector.exception.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {
    private Date timestamp;
    private String error;
    private List<Detail> detail;
}
