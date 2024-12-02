package net.comicorp.collector.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.comicorp.collector.dto.RefreshDTO;
import net.comicorp.collector.dto.TokenDTO;
import net.comicorp.collector.service.AuthService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.stream.Collectors;

import static net.comicorp.collector.constant.Constants.*;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final JwtEncoder jwtAccessEncoder;
    private final JwtEncoder jwtRefreshEncoder;
    private final JwtDecoder jwtRefreshDecoder;
    private Instant now;

    public AuthServiceImpl(JwtEncoder jwtAccessEncoder,
                           @Qualifier("jwtRefreshEncoder") JwtEncoder jwtRefreshEncoder,
                           @Qualifier("jwtRefreshDecoder") JwtDecoder jwtRefreshDecoder) {
        this.jwtAccessEncoder = jwtAccessEncoder;
        this.jwtRefreshEncoder = jwtRefreshEncoder;
        this.jwtRefreshDecoder = jwtRefreshDecoder;
    }

    @Override
    public TokenDTO login(Authentication authentication) {

        now = Instant.now();

        Instant accessTokenExpiresAt = now.plusSeconds(EXPIRY);
        Instant refreshTokenExpiresAt = now.plus(7, ChronoUnit.DAYS);

        Jwt accessToken = buildToken(ACCESS_TOKEN, authentication, accessTokenExpiresAt, jwtAccessEncoder);
        Jwt refreshToken = buildToken(REFRESH_TOKEN, authentication, refreshTokenExpiresAt, jwtRefreshEncoder);

        return TokenDTO
                .builder()
                .accessToken(accessToken.getTokenValue())
                .refreshToken(refreshToken.getTokenValue())
                .tokenType(TOKEN_PREFIX)
                .expiresIn(EXPIRY)
                .build();
    }

    @Override
    public TokenDTO refreshToken(RefreshDTO refreshDTO) throws JwtException{
        Jwt decodedRefreshToken = jwtRefreshDecoder.decode(refreshDTO.getRefreshToken());

        now = Instant.now();

        Instant accessTokenExpiresAt = now.plusSeconds(EXPIRY);

        Jwt accessToken = buildToken(decodedRefreshToken, accessTokenExpiresAt, jwtAccessEncoder);
        Jwt refreshToken = buildRefreshToken(decodedRefreshToken);

        return TokenDTO
                .builder()
                .accessToken(accessToken.getTokenValue())
                .refreshToken(refreshToken.getTokenValue())
                .tokenType(TOKEN_PREFIX)
                .expiresIn(EXPIRY)
                .build();
    }

    private Jwt buildToken(String type, Authentication authentication, Instant expiresAt, JwtEncoder encoder) {

        String scope = buildScopes(authentication.getAuthorities());

        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .issuer(ISSUER)
                .issuedAt(now)
                .expiresAt(expiresAt)
                .subject(authentication.getName())
                .claim(SCOPE, scope)
                .claim(TYP, type)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims));
    }

    private Jwt buildToken(Jwt jwt, Instant expiresAt, JwtEncoder encoder) {

        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .issuer(ISSUER)
                .issuedAt(now)
                .expiresAt(expiresAt)
                .subject(jwt.getSubject())
                .claim(SCOPE, jwt.getClaimAsString(SCOPE))
                .claim(TYP, jwt.getClaimAsString(TYP))
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims));
    }

    private Jwt buildRefreshToken(Jwt jwt) {
        return Duration.between(now, jwt.getExpiresAt()).toDays() < 1
                ? buildToken(jwt, now.plus(7, ChronoUnit.DAYS), jwtRefreshEncoder)
                : jwt;
    }

    private String buildScopes(Collection<? extends GrantedAuthority> roles) {
        return roles
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
}
