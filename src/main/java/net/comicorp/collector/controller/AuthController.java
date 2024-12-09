package net.comicorp.collector.controller;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.dto.RefreshDTO;
import net.comicorp.collector.dto.TokenDTO;
import net.comicorp.collector.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<TokenDTO> login(Authentication authentication) {
        return ResponseEntity.ok(authService.login(authentication));
    }

    @PostMapping("/refresh")
    ResponseEntity<TokenDTO> refresh(@RequestBody RefreshDTO refreshDTO) {
        return (refreshDTO.getRefreshToken() == null || refreshDTO.getRefreshToken().isBlank())
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(authService.refreshToken(refreshDTO));
    }
}
