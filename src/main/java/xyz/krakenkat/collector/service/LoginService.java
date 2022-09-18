package xyz.krakenkat.collector.service;

import xyz.krakenkat.collector.dto.TokenDTO;
import org.springframework.security.core.Authentication;

public interface LoginService {
    TokenDTO login(Authentication authentication);
}
