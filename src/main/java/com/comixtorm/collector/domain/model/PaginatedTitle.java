package com.comixtorm.collector.domain.model;

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
public class PaginatedTitle {
    private List<Map<String, Integer>> pageInfo;
    private List<Title> resultData;
}
