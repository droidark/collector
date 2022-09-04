package xyz.krakenkat.collector.domain.model;


import lombok.*;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private ObjectId publisherId;
    private ObjectId titleId;
    private ObjectId issueId;
    private Date collectedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(issueId, item.issueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueId);
    }
}
