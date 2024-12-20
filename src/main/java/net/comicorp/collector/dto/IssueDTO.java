package net.comicorp.collector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(collectionRelation = "issues")
public class IssueDTO {

    private String name;
    private String key;
    private Float number;
    private String cover;
    private Integer pages;
    private Float printedPrice;
    private Float digitalPrice;
    private String currency;
    private Date releaseDate;
    private String shortReview;
    private String event;
    private String storyArch;
    private String isbn;
    private Long barcode;
    private Integer edition;
    private Boolean variant;
    private Integer likesCounter;
    private Integer dislikesCounter;
    private Boolean collected;
}
