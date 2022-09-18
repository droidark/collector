package xyz.krakenkat.collector.controller;

import xyz.krakenkat.collector.dto.TokenDTO;
import xyz.krakenkat.collector.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping("/login")
    public TokenDTO login(Authentication authentication) {
        return loginService.login(authentication);
    }
}
