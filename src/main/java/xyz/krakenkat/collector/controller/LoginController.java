package xyz.krakenkat.collector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.dto.TokenDTO;
import xyz.krakenkat.collector.service.LoginService;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public TokenDTO login(Authentication authentication) {
        return loginService.login(authentication);
    }
}
