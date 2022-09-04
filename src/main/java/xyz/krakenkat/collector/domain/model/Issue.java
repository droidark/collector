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
import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Issue {
    @Id
    private String id;
    private String title;
    private String name;
    private String key;
    private Double number;
    private String cover;
    private Integer pages;
    private Double printedPrice;
    private Double digitalPrice;
    private String currency;
    private Date releaseDate;
    private String shortReview;
    private String event;
    private String storyArch;
    private String isbn10;
    private String isbn13;
    private Long barcode;
    private Integer edition;
    private Boolean variant;
    private String variantOf;
    private Integer likesCounter;
    private Integer dislikesCounter;
    private List<Like> likes;
    private Map<String, String> authors;
}
