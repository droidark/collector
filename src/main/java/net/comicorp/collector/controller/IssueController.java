package net.comicorp.collector.controller;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.service.IssueService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static net.comicorp.collector.util.Utilities.buildPage;
import static net.comicorp.collector.util.Utilities.buildPagedModel;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/{issueKey}")
    ResponseEntity<PagedModel<IssueDTO>> retrieveIssueByPublisherKeyTitleKeyAndIssueKey(
            @PathVariable String  issueKey,
            @RequestParam(name = "publisher") String publisherKey,
            @RequestParam(name = "title") String titleKey,
            @RequestParam(name = "variant", required = false) Boolean variant,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "variant,asc") String[] sort) {

        Page<IssueDTO> issueDTOPage = issueService.getByKeyAndPublisherKeyAndTitleKey(publisherKey, titleKey, issueKey, variant, buildPage(page, size, sort));
        return issueDTOPage.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(issueDTOPage));
    }
}
