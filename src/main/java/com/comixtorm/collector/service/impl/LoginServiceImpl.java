package com.comixtorm.collector.service.impl;

import com.comixtorm.collector.domain.model.response.Token;
import com.comixtorm.collector.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service("loginService")
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final long expiry = 3600L;
    private JwtEncoder jwtEncoder;

    @Override
    public Token login(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims));

        return Token
                .builder()
                .accessToken(jwt.getTokenValue())
                .tokenType("Bearer")
                .expiresIn(expiry)
                .build();
    }
}
