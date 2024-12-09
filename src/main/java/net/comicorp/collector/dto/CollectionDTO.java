package net.comicorp.collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionDTO {
    private String publisherKey;
    private String titleKey;
    private List<String> issuesKeys;
}
