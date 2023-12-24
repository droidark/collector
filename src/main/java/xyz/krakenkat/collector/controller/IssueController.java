package xyz.krakenkat.collector.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.krakenkat.collector.dto.IssueDTO;

@Tag(name = "Issue", description = "Issue API to execute CRUD operations")
@RequestMapping("/issues")
public interface IssueController {

    ResponseEntity<IssueDTO> retrieveIssueByPublisherKeyTitleKeyAndIssueKey(
            String publisherKey,
            String titleKey,
            String issueKey);

}
