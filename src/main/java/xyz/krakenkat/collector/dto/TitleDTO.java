package xyz.krakenkat.collector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Title DTO")
public class TitleDTO {

    @Schema(description = "The title key should consist of lowercase letters or numbers. " +
            "Spaces in the title key should be replaced with dashes.", example = "saint-seiya")
    private String key;

    @Schema(description = "Title name", example = "Saint Seiya")
    private String name;

    @Schema(description = "Title cover URL", example = "saint-seiya.png")
    private String cover;

    @Schema(description = "Title demography", example = "Shōnen")
    private String demography;

    @Schema(description = "Title format edition", example = "Kanzenban")
    private String format;

    @Schema(description = "Title type", example = "manga")
    private String type;

    @Schema(description = "Title publishing frequency", example = "bimonthly")
    private String frequency;

    @Schema(description = "Title publishing status", example = "ongoing")
    private String status;

    @Schema(description = "Title release date", example = "2023-12-02")
    private Date releaseDate;

    @Schema(description = "Title total issues", example = "28")
    private int totalIssues;

    @Schema(description = "List of genres", example = "[Fantasy, Martial Arts, Mythological]")
    private List<String> genres;

    @Schema(description = "Map with staff role", example = "[ Author => [Masami Kurumada] ]")
    private Map<String, List<String>> authors;
}
