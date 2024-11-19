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
import net.comicorp.collector.dto.PublisherDTO;
import net.comicorp.collector.dto.TitleDTO;
import net.comicorp.collector.exception.NoContentException;

import static net.comicorp.collector.constant.Constants.*;

@Tag(name = "Publisher", description = "Publisher API to execute CRUD operations")
@RequestMapping("/publishers")
public interface PublisherController {

    @Operation(
            summary = "Get all publishers",
            description = "Retrieve all publishers supported by collector.",
            tags = {"publisher"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PublisherDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            )
    })
    @GetMapping
    ResponseEntity<PagedModel<PublisherDTO>> retrieveAllPublishers(
            @Parameter(description = PAGE_DESCRIPTION) @RequestParam(defaultValue = "0") int page,
            @Parameter(description = SIZE_DESCRIPTION) @RequestParam(defaultValue = "10") int size,
            @Parameter(description = SORT_DESCRIPTION) @RequestParam(defaultValue = "name,asc") String[] sort);

    @Operation(
            summary = "Get publisher by key",
            description = "Retrieve the publisher that match with the publisher-key",
            tags = {"publisher"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = PublisherDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
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
    @GetMapping("/{key}")
    ResponseEntity<PublisherDTO> retrievePublisherByKey(
            @Parameter(description = "Publisher key, cannot be empty", required = true) @PathVariable String key) throws NoContentException;


    @Operation(
            summary = "Get all titles edited by specific publisher",
            description = "Retrieve all published titles by specific publisher.",
            tags = {"publisher", "title"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TitleDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            )
    })
    @GetMapping("/{publisherKey}/titles")
    ResponseEntity<PagedModel<TitleDTO>> retrieveAllTitlesByKey(
            @Parameter(description = "Publisher key, cannot be empty", required = true) @PathVariable String publisherKey,
            @Parameter(description = PAGE_DESCRIPTION) @RequestParam(defaultValue = "0") int page,
            @Parameter(description = SIZE_DESCRIPTION) @RequestParam(defaultValue = "10") int size,
            @Parameter(description = SORT_DESCRIPTION) @RequestParam(defaultValue = "name,asc") String[] sort);
}
