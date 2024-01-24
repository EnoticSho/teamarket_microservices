package com.example.teamarket.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

/**
 * This utility class provides methods for working with JWT tokens.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Retrieves all claims from a JWT token.
     *
     * @param token The JWT token to extract claims from.
     * @return A Claims object containing the JWT claims.
     * @throws JwtException If there is an issue parsing the JWT token.
     */
    public Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new JwtException("Failed to parse JWT token", e);
        }
    }

    /**
     * Checks if a JWT token is invalid (e.g., expired).
     *
     * @param token The JWT token to check for validity.
     * @return true if the token is invalid, false otherwise.
     */
    public boolean isInvalid(String token) {
        return isTokenExpired(token);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenExpired(String token) {
        return getAllClaimsFromToken(token)
                .getExpiration()
                .before(new Date());
    }
}
