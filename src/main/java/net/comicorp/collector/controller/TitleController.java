package net.comicorp.collector.controller;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.exception.NoContentException;
import net.comicorp.collector.service.TitleService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static net.comicorp.collector.util.Utilities.buildPage;
import static net.comicorp.collector.util.Utilities.buildPagedModel;

@RestController
@RequestMapping("/titles")
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;

    @GetMapping("/{key}")
    ResponseEntity<TitleDTO> retrieveByKeyAndPublisherKey(
            @PathVariable String key,
            @RequestParam(name = "publisher") String publisherKey)
            throws FieldNotValidException, NoContentException {
        return ResponseEntity.ok(titleService.getTitleByKeyAndPublisherKey(key, publisherKey));
    }

    @GetMapping("/{titleKey}/issues")
    ResponseEntity<PagedModel<IssueDTO>> retrieveIssuesByTitleKeyAndPublisherKey(
            @PathVariable String titleKey,
            @RequestParam(name = "publisher") String publisherKey,
            @RequestParam(name = "variant", required = false) Boolean variant,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "number,asc") String[] sort) {

        Page<IssueDTO> issueDTOPage = titleService.getAllIssuesByPublisherKeyAndTitleKey(publisherKey, titleKey, variant, buildPage(page, size, sort));
        return issueDTOPage.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(buildPagedModel(issueDTOPage));
    }
}
