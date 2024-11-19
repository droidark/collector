package net.comicorp.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.controller.IssueController;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.service.IssueService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static net.comicorp.collector.util.Utilities.buildPage;
import static net.comicorp.collector.util.Utilities.buildPagedModel;

@RestController
@RequiredArgsConstructor
public class IssueControllerImpl implements IssueController {

    private final IssueService issueService;

    @Override
    public ResponseEntity<PagedModel<IssueDTO>> retrieveIssueByPublisherKeyTitleKeyAndIssueKey(String issueKey, String publisherKey, String titleKey, Boolean variant, int page, int size, String[] sort) {
        Page<IssueDTO> issueDTOPage = issueService.getByKeyAndPublisherKeyAndTitleKey(publisherKey, titleKey, issueKey, variant, buildPage(page, size, sort));
        return issueDTOPage.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(issueDTOPage));
    }
}
