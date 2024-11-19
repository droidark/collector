package net.comicorp.collector.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "author")
    private Set<TitleAuthorRole> titleAuthorRoles;
}
