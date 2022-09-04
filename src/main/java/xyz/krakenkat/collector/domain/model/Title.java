package xyz.krakenkat.collector.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Title {
    @Id
    private String id;
    private String key;
    private String name;
    private String cover;
    private String demography;
    private String status;
    private List<String> genres;
    private String publisher;
    private Date releaseDate;
    private int totalIssues;
}
