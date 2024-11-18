package net.comicorp.collector.service;

import net.comicorp.collector.dto.IssueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IssueService {

    Page<IssueDTO> getByKeyAndPublisherKeyAndTitleKey(String publisherKey, String titleKey, String key, Boolean variant, Pageable pageable);
}
