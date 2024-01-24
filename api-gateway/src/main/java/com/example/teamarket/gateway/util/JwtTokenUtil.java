package com.example.teamarket.gateway.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * This utility class provides methods for extracting JWT tokens from HTTP requests.
 */
@Component
public class JwtTokenUtil {

    /**
     * Extracts a JWT token from the provided ServerHttpRequest.
     *
     * @param request The ServerHttpRequest to extract the token from.
     * @return An Optional containing the extracted token, or an empty Optional if no token is found.
     */
    public static Optional<String> extractToken(ServerHttpRequest request) {
        String authorizationHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return Optional.of(authorizationHeader.substring(7));
        }
        return Optional.empty();
    }
}
