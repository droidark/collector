package net.comicorp.collector.domain.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.comicorp.collector.constant.SocialNetworkType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public class SocialNetwork {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SocialNetworkType type;

    @Column(name = "username")
    private String username;

    @Column(name = "url")
    private String url;
}
