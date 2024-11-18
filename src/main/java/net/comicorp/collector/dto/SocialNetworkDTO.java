package net.comicorp.collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.comicorp.collector.constant.SocialNetworkType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialNetworkDTO {

    private SocialNetworkType type;

    private String username;

    private String url;
}
