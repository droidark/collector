package xyz.krakenkat.collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherDTO {

    private String name;

    private String key;

    private String url;

    private String logo;

    private String information;

    private List<SocialNetworkDTO> socialNetworks;
}
