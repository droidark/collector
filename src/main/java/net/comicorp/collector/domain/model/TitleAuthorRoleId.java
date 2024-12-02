package net.comicorp.collector.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@EqualsAndHashCode
public class TitleAuthorRoleId implements Serializable {
    private Long titleId;
    private Long authorId;
    private Long roleId;
}
