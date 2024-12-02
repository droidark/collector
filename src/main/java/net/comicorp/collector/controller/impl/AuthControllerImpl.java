package net.comicorp.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.comicorp.collector.controller.AuthController;
import net.comicorp.collector.dto.RefreshDTO;
import net.comicorp.collector.dto.TokenDTO;
import net.comicorp.collector.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public ResponseEntity<TokenDTO> login(Authentication authentication) {
        return ResponseEntity.ok(authService.login(authentication));
    }

    @Override
    public ResponseEntity<TokenDTO> refresh(RefreshDTO refreshDTO) {
        return (refreshDTO.getRefreshToken() == null || refreshDTO.getRefreshToken().isBlank())
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(authService.refreshToken(refreshDTO));
    }
}
