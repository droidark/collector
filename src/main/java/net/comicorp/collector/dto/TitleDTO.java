package net.comicorp.collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Relation(collectionRelation = "titles")
public class TitleDTO {
    private String name;
    private String key;
    private String cover;
    private String demography;
    private String format;
    private String frequency;
    private Date releaseDate;
    private String status;
    private String type;
    private Integer totalIssues;
    private List<String> genres;
    private Map<String, List<String>> authors;
}
