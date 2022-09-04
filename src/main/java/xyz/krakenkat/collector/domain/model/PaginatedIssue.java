package xyz.krakenkat.collector.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
