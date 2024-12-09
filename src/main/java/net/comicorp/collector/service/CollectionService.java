package net.comicorp.collector.service;

import net.comicorp.collector.dto.CollectionDTO;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.dto.TitleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CollectionService {
    Page<TitleDTO> getTitlesByUsername(String username, Pageable pageable);
    Page<IssueDTO> getIssuesWithDynamicFilters(String username, String titleKey, String publisherKey, Boolean collected, Boolean variant, Pageable pageable);
    void addItemToCollection(String username, CollectionDTO collectionDTO);
    void removeItemFromCollection(String username, CollectionDTO collectionDTO);
}
