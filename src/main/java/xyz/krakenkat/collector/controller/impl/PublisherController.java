package xyz.krakenkat.collector.controller.impl;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.util.Constants;

@Tag(name = "Publisher", description = "Publisher API to execute CRUD operations")
@RequestMapping("/publishers")
public interface PublisherController {

    @Operation(
            summary = "Get all publishers",
            description = "Retrieve all publishers supported by collector.",
            tags = {"publisher"})
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PublisherDTO.class)), mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            )
    })
    @GetMapping
    ResponseEntity<Page<PublisherDTO>> retrieveAllPublishers(
            @Parameter(description = Constants.PAGE_DESCRIPTION) @RequestParam(defaultValue = "0") int page,
            @Parameter(description = Constants.SIZE_DESCRIPTION) @RequestParam(defaultValue = "10") int size,
            @Parameter(description = Constants.SORT_DESCRIPTION) @RequestParam(defaultValue = "name,desc") String[] sort);

    @Operation(
            summary = "Get publisher by key",
            description = "Retrieve the publisher that match with the publisher-key",
            tags = {"publisher"})
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = PublisherDTO.class), mediaType = "application/json")),
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
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TitleDTO.class)), mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            )
    })
    @GetMapping("/{key}/titles")
    ResponseEntity<Page<TitleDTO>> retrieveTitlesByKey(
            @Parameter(description = "Publisher key, cannot be empty", required = true) @PathVariable String key,
            @Parameter(description = Constants.PAGE_DESCRIPTION) @RequestParam(defaultValue = "0") int page,
            @Parameter(description = Constants.SIZE_DESCRIPTION) @RequestParam(defaultValue = "10") int size,
            @Parameter(description = Constants.SORT_DESCRIPTION) @RequestParam(defaultValue = "name,asc") String[] sort);

//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
//    @PostMapping
//    ResponseEntity<String> createPublisher(Authentication authentication);
}
