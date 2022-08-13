package com.comixtorm.collector.service;

import com.comixtorm.collector.domain.model.response.Token;
import org.springframework.security.core.Authentication;

public interface LoginService {
    Token login(Authentication authentication);
}
