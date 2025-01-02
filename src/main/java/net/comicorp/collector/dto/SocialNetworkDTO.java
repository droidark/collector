package net.comicorp.collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.comicorp.collector.constant.SocialNetworkType;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialNetworkDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private SocialNetworkType type;

    private String username;

    private String url;
}
