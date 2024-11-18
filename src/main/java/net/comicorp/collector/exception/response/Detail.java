package net.comicorp.collector.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Detail {

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rejectedValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;
}
