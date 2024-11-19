package net.comicorp.collector.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "publishers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lookup_key")
    private String key;

    @Column(name = "url")
    private String url;

    @Column(name = "logo")
    private String logo;

    @Column(name = "information")
    private String information;

    @JsonManagedReference
    @JsonIgnoreProperties("publisher")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<PublisherSocialNetwork> publisherSocialNetworks;
}
