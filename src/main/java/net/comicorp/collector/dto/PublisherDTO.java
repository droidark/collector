package net.comicorp.collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Relation(collectionRelation = "publishers")
public class PublisherDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private String key;

    private String url;

    private String logo;

    private String information;

    private List<SocialNetworkDTO> socialNetworks;
}
