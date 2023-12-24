package xyz.krakenkat.collector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Social Network DTO")
public class SocialNetworkDTO {

    @Schema(description = "Key to identify the social network. This key is composed ty two lowercase letters.", example = "fb")
    private String key;

    @Schema(description = "Username displayed in the social network.", example = "foo")
    private String nickname;

    @Schema(description = "URL to access the social network.", example = "https://www.facebook.com/foo")
    private String url;
}
