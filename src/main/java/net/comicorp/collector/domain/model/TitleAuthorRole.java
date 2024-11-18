package net.comicorp.collector.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "titles_authors_roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TitleAuthorRole {

    @EmbeddedId
    private TitleAuthorRoleId id;

    @ManyToOne
    @MapsId("titleId")
    @JoinColumn(name = "title_id")
    private Title title;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;
}
