package xyz.krakenkat.collector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.KeysDTO;
import xyz.krakenkat.collector.dto.TitleDTO;

import java.util.Optional;

public interface CollectionService {
    Page<TitleDTO> getTitlesByUsername(String username, Pageable pageable);
    Page<IssueDTO> getIssuesByUsernameAndTitleId(String username, Optional<String> publisherKey, String titleKey, boolean variant, Pageable pageable);
    void addItemsToCollection(KeysDTO keysDTO, String username);
    void removeItemsToCollection(KeysDTO keysDTO, String username);
}
