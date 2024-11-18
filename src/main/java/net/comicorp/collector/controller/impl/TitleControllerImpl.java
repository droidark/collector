package net.comicorp.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.controller.TitleController;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.exception.NoContentException;
import net.comicorp.collector.service.TitleService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static net.comicorp.collector.util.Utilities.buildPage;
import static net.comicorp.collector.util.Utilities.buildPagedModel;

@RestController
@RequiredArgsConstructor
public class TitleControllerImpl implements TitleController {

    private final TitleService titleService;

    @Override
    public ResponseEntity<TitleDTO> retrieveByKeyAndPublisherKey(String key, String publisherKey) throws FieldNotValidException, NoContentException {
        return ResponseEntity.ok(titleService.getTitleByKeyAndPublisherKey(key, publisherKey));
    }

    @Override
    public ResponseEntity<PagedModel<IssueDTO>> retrieveIssuesByTitleKeyAndPublisherKey(String titleKey, String publisherKey, Boolean variant, int page, int size, String[] sort) {
        Page<IssueDTO> issueDTOPage = titleService.getAllIssuesByPublisherKeyAndTitleKey(publisherKey, titleKey, variant, buildPage(page, size, sort));
        return issueDTOPage.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(issueDTOPage));
    }
}
