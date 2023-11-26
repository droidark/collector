package xyz.krakenkat.collector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Publisher DTO")
public class PublisherDTO {

    @Schema(description = "Publisher name", example = "Viz Media")
    private String name;

    @Schema(description = "The publisher key should consist of lowercase letters or numbers. " +
            "Spaces in the publisher key should be replaced with dashes.", example = "viz-media")
    private String key;

    @Schema(description = "Publisher URL", example = "https://www.viz.com/")
    private String url;

    @Schema(description = "Publisher logo URL", example = "viz-logo.png")
    private String logo;

    @Schema(description = "Publisher general information", example = "Small manga and light-novel publisher")
    private String information;

    private List<SocialNetworkDTO> socialNetworks;
}
