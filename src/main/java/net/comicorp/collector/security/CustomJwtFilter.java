package net.comicorp.collector.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class CustomJwtFilter extends OncePerRequestFilter {

    private final JwtDecoder jwtRefreshDecoder;

    private final RequestMatcher uriMatcher =
            new AntPathRequestMatcher("/collector/v1/refresh", HttpMethod.POST.name());

    public CustomJwtFilter(@Qualifier("jwtRefreshDecoder") JwtDecoder jwtRefreshDecoder) {
        this.jwtRefreshDecoder = jwtRefreshDecoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("Processing request for URI: {}", request.getRequestURI());

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try {
                Jwt jwt = jwtRefreshDecoder.decode(token); // Only refresh tokens are expected
                SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(jwt));
                log.info("Authentication set for subject: {}", jwt.getSubject());
            } catch (JwtException e) {
                log.error("Invalid JWT token: {}", e.getMessage());
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return; // Stop further processing
            }
        } else {
            log.warn("Authorization header is missing or invalid for /collector/v1/refresh");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is required");
            return;
        }

        // Proceed with the request
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !uriMatcher.matches(request);
    }
}
