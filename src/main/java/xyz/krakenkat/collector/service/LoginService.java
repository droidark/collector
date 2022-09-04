package xyz.krakenkat.collector.service;

import xyz.krakenkat.collector.domain.model.response.Token;
import org.springframework.security.core.Authentication;

public interface LoginService {
    Token login(Authentication authentication);
}
