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
public class IssueDTO {
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
    private String isbn;
    private Long barcode;
    private Integer edition;
    private Boolean variant;
    private Integer likesCounter;
    private Integer dislikesCounter;
    private List<LikeDTO> likes;
}
