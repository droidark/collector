package net.comicorp.collector.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.exception.NoContentException;

import static net.comicorp.collector.constant.Constants.*;

@Tag(name = "Title", description = "Title API to execute CRUD operations")
@RequestMapping("/titles")
public interface TitleController {

    @Operation(
            summary = "Get title by title-key and publisher-key",
            description = "Retrieve title based on title-key and publisher-key parameters.",
            tags = {"title"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = TitleDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            )
    })
    @GetMapping("/{key}")
    ResponseEntity<TitleDTO> retrieveByKeyAndPublisherKey(
            @Parameter(description = "Title key, cannot be empty", required = true) @PathVariable String key,
            @Parameter(description = "Publisher key, cannot be empty", required = true) @RequestParam(name = "publisher") String publisherKey)
            throws FieldNotValidException, NoContentException;

    @Operation(
            summary = "Get issues by title-key and publisher-key",
            description = "Retrieve issues based on title-key and publisher-key parameters.",
            tags = {"title", "issue"}
    )
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
    @GetMapping("/{titleKey}/issues")
    ResponseEntity<PagedModel<IssueDTO>> retrieveIssuesByTitleKeyAndPublisherKey(
            @Parameter(description = "Title key, cannot be empty", required = true) @PathVariable String titleKey,
            @Parameter(description = "Publisher key", required = true) @RequestParam(name = "publisher") String publisherKey,
            @Parameter(description = VARIANT_DESCRIPTION) @RequestParam(name = "variant", required = false) Boolean variant,
            @Parameter(description = PAGE_DESCRIPTION) @RequestParam(defaultValue = "0") int page,
            @Parameter(description = SIZE_DESCRIPTION) @RequestParam(defaultValue = "10") int size,
            @Parameter(description = SORT_DESCRIPTION) @RequestParam(defaultValue = "number,asc") String[] sort);
}
