package net.comicorp.collector.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.comicorp.collector.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "User", description = "User API to execute CRUD operations")
@RequestMapping("/users")
public interface UserController {

    @Operation(
            summary = "Create a new user",
            description = "Create a new user",
            tags = {"user"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = UserDTO.class), mediaType = APPLICATION_JSON_VALUE)
            )
    })
    @PostMapping("/signup")
    ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO);
}
