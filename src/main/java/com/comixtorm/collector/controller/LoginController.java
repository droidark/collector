package com.comixtorm.collector.controller;

import com.comixtorm.collector.domain.model.response.Token;
import com.comixtorm.collector.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping(value = "/login")
    public Token login(Authentication authentication) {
        return loginService.login(authentication);
    }

    // TODO
    // logout
}
