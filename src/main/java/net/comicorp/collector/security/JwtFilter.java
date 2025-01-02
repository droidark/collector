package net.comicorp.collector.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.comicorp.collector.exception.response.Detail;
import net.comicorp.collector.exception.response.ExceptionResponse;
import net.comicorp.collector.service.RedisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "");

            // Check if the token is blacklisted
            if (redisService.isTokenBlacklisted(token)) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write(buildResponse());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String buildResponse() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .error(HttpStatus.UNAUTHORIZED.toString())
                .timestamp(Date.from(Instant.now()))
                .detail(List.of(Detail
                        .builder()
                        .message("Token is blacklisted")
                        .build()))
                .build();
        return objectMapper.writeValueAsString(exceptionResponse);
    }
}
