package xyz.krakenkat.collector.domain.model.query;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ids {
    private ObjectId publisherId;
    private ObjectId titleId;
    private ObjectId issueId;
}
