package xyz.krakenkat.collector.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class TitleAuthorRoleId implements Serializable {
    private Long titleId;
    private Long authorId;
    private Long roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleAuthorRoleId that = (TitleAuthorRoleId) o;
        return Objects.equals(titleId, that.titleId) && Objects.equals(authorId, that.authorId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleId, authorId, roleId);
    }
}
