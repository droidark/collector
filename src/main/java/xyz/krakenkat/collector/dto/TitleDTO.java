package xyz.krakenkat.collector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TitleDTO {
    private String key;
    private String name;
    private String cover;
    private String demography;
    private String format;
    private String type;
    private String frequency;
    private String status;
    private List<String> genres;
    private Map<String, List<String>> authors;
    private Date releaseDate;
    private int totalIssues;
}
