package net.comicorp.collector.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.comicorp.collector.dto.RefreshDTO;
import net.comicorp.collector.dto.TokenDTO;
import net.comicorp.collector.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
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

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(authService.logout(token));
    }
}
