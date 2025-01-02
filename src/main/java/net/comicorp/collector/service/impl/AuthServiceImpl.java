package net.comicorp.collector.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.comicorp.collector.dto.RefreshDTO;
import net.comicorp.collector.dto.TokenDTO;
import net.comicorp.collector.service.AuthService;
import net.comicorp.collector.service.RedisService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static net.comicorp.collector.constant.Constants.*;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final JwtEncoder jwtAccessEncoder;
    private final JwtDecoder jwtAccessTokenDecoder;
    private final JwtEncoder jwtRefreshEncoder;
    private final JwtDecoder jwtRefreshDecoder;
    private final RedisService redisService;

    private Instant now;

    public AuthServiceImpl(JwtEncoder jwtAccessEncoder,
                           JwtDecoder jwtAccessTokenDecoder,
                           @Qualifier("jwtRefreshEncoder") JwtEncoder jwtRefreshEncoder,
                           @Qualifier("jwtRefreshDecoder") JwtDecoder jwtRefreshDecoder,
                           RedisService redisService) {
        this.jwtAccessEncoder = jwtAccessEncoder;
        this.jwtAccessTokenDecoder = jwtAccessTokenDecoder;
        this.jwtRefreshEncoder = jwtRefreshEncoder;
        this.jwtRefreshDecoder = jwtRefreshDecoder;
        this.redisService = redisService;
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

    @Override
    public String logout(String token) {
        // Extract token
        String jwt = token.substring(7);

        // Decode the token to find the expiration time
        Jwt jwtDecoded = jwtAccessTokenDecoder.decode(jwt);

        long expirationTime = jwtDecoded.getExpiresAt().getEpochSecond() - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        redisService.blacklistToken(jwt, expirationTime);

        return "Logged out successfully";
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
        return buildToken(jwt, now.plus(7, ChronoUnit.DAYS), jwtRefreshEncoder);
    }

    private String buildScopes(Collection<? extends GrantedAuthority> roles) {
        return roles
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
}
