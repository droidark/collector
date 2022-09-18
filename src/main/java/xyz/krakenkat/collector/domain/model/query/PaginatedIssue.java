package xyz.krakenkat.collector.domain.model.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.krakenkat.collector.domain.model.Issue;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginatedIssue {
    private List<Map<String, Integer>> pageInfo;
    private List<Issue> resultData;
}
