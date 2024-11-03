package xyz.krakenkat.collector.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.NoContentException;

@Tag(name = "Title", description = "Title API to execute CRUD operations")
@RequestMapping("/titles")
public interface TitleController {

    @Operation(
            summary = "Get title by title-id",
            description = "Retrieve title based on title-id.",
            tags = {"title"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = TitleDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping("/{titleId}")
    ResponseEntity<TitleDTO> retrieveTitleById(
            @Parameter(description = "Title id, cannot be empty", required = true) @PathVariable Long titleId) throws NoContentException;
}
