package net.comicorp.collector.service;

import net.comicorp.collector.dto.IssueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.exception.NoContentException;

public interface TitleService {

    Page<TitleDTO> getAllTitlesByPublisherKey(String publisherKey, Pageable pageable) throws NoContentException;

    TitleDTO getTitleByKeyAndPublisherKey(String key, String publisherKey) throws FieldNotValidException, NoContentException;

    Page<IssueDTO> getAllIssuesByPublisherKeyAndTitleKey(String publisherKey, String titleKey, Boolean variant, Pageable pageable) throws FieldNotValidException ;
}
