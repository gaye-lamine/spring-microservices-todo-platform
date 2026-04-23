package com.example.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

/**
 * Validates JWT tokens signed by user-service.
 * Both services share the same Base64-encoded secret key.
 * JJWT 0.9.1 (user-service) decodes it via DatatypeConverter.parseBase64Binary.
 * JJWT 0.11.5 (gateway) decodes it via Decoders.BASE64 — same result.
 */
@Component
public class JwtUtils {
    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret:yLd/5MwNwJqsISm0jmtfJoufVvusFrCgnjbelAbTce17KbTt2qEf/BmOx7SXbnYAGH4Gg/NoDNPHoe71sI6fWg==}")
    private String secretKey;

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("[JWT] validateToken failed: {} — {}", e.getClass().getSimpleName(), e.getMessage());
            return false;
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
