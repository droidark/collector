package xyz.krakenkat.collector.domain.model.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.krakenkat.collector.domain.model.Title;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginatedTitle {
    private List<Map<String, Integer>> pageInfo;
    private List<Title> resultData;
}
