package net.comicorp.collector.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.comicorp.collector.dto.RefreshDTO;
import net.comicorp.collector.dto.TokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Login", description = "Endpoint to login in API environment")
@RequestMapping("/auth")
public interface AuthController {

    @Operation(
            summary = "Login",
            description = "Login",
            tags = {"login"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = TokenDTO.class), mediaType = APPLICATION_JSON_VALUE)
            )
    })
    @PostMapping("/login")
    ResponseEntity<TokenDTO> login(Authentication authentication);

    @Operation(
            summary = "Refresh",
            description = "Refresh a expired token",
            tags = {"login"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = TokenDTO.class), mediaType = APPLICATION_JSON_VALUE)
            )
    })
    @PostMapping("/refresh")
    ResponseEntity<TokenDTO> refresh(@RequestBody RefreshDTO refreshDTO);

}
