package net.comicorp.collector.service.impl;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private static final String KEY = "BLACKLISTED";

    private final StringRedisTemplate redisTemplate;

    @Override
    public void blacklistToken(String token, long expirationTime) {
        redisTemplate.opsForValue().set(token, KEY, expirationTime, TimeUnit.SECONDS);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey(token);
    }
}
