package net.comicorp.collector.service;

public interface RedisService {
    void blacklistToken(String token, long expirationTime);
    boolean isTokenBlacklisted(String token);
}
