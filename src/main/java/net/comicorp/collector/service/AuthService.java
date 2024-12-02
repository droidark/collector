package net.comicorp.collector.service;

import net.comicorp.collector.dto.RefreshDTO;
import net.comicorp.collector.dto.TokenDTO;
import org.springframework.security.core.Authentication;

public interface AuthService {
    TokenDTO login(Authentication authentication);
    TokenDTO refreshToken(RefreshDTO refreshDTO);
}
