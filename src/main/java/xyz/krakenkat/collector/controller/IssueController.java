package xyz.krakenkat.collector.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.krakenkat.collector.dto.IssueDTO;

import static xyz.krakenkat.collector.util.Constants.*;
import static xyz.krakenkat.collector.util.Constants.SORT_DESCRIPTION;

@Tag(name = "Issue", description = "Issue API to execute CRUD operations")
@RequestMapping("/issues")
public interface IssueController {

    @Operation(
            summary = "Get issues (if variant covers exists) by publisher-key, title-key and issue-key",
            description = "Retrieve specific issues based on publisher-key, title-key and issue-key parameters.",
            tags = {"title", "issue"}
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = IssueDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content",
                    content = @Content),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            )
    })
    @GetMapping("/{issueKey}")
    ResponseEntity<Page<IssueDTO>> retrieveIssueByPublisherKeyTitleKeyAndIssueKey(
            @Parameter(description = "Issue key, cannot be empty", required = true) @PathVariable String  issueKey,
            @Parameter(description = "Publisher key", required = true) @RequestParam(name = "publisher") String publisherKey,
            @Parameter(description = "Title key", required = true) @RequestParam(name = "title") String titleKey,
            @Parameter(description = VARIANT_DESCRIPTION) @RequestParam(name = "variant", defaultValue = "all") String variant,
            @Parameter(description = PAGE_DESCRIPTION) @RequestParam(defaultValue = "0") int page,
            @Parameter(description = SIZE_DESCRIPTION) @RequestParam(defaultValue = "10") int size,
            @Parameter(description = SORT_DESCRIPTION) @RequestParam(defaultValue = "variant,asc") String[] sort);
}
