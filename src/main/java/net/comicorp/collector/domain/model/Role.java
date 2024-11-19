package net.comicorp.collector.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<TitleAuthorRole> titleAuthorRoles;
}
