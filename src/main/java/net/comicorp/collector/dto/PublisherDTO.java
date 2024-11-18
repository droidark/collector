package net.comicorp.collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Relation(collectionRelation = "publishers")
public class PublisherDTO {

    private String name;

    private String key;

    private String url;

    private String logo;

    private String information;

    private List<SocialNetworkDTO> socialNetworks;
}
