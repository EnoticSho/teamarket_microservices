package com.example.teamarket.gateway.filter;

import com.example.teamarket.gateway.util.JwtTokenUtil;
import com.example.teamarket.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * This class represents a filter for token-based authentication in a gateway.
 */
@Component
public class TokenAuthenticationFilter extends AbstractGatewayFilterFactory<TokenAuthenticationFilter.Config> {
    private final JwtUtil jwtUtil;

    /**
     * Constructor for the TokenAuthenticationFilter class.
     *
     * @param jwtUtil An instance of JwtUtil used for JWT operations.
     */
    public TokenAuthenticationFilter(JwtUtil jwtUtil) {
        super(TokenAuthenticationFilter.Config.class);
        this.jwtUtil = jwtUtil;
    }

    /**
     * Configuration class for the TokenAuthenticationFilter.
     */
    public static class Config {
    }

    /**
     * Applies the TokenAuthenticationFilter to the given ServerWebExchange.
     *
     * @param config The configuration for the TokenAuthenticationFilter.
     * @return A GatewayFilter that performs token-based authentication.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            Optional<String> token = JwtTokenUtil.extractToken(exchange.getRequest());

            if (token.isEmpty() || jwtUtil.isInvalid(token.get())) {
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or missing token"));
            }

            try {
                Claims claims = jwtUtil.getAllClaimsFromToken(token.get());
                List<String> roles = claims.get("roles", List.class);
                String rolesString = String.join(",", roles);

                String email = claims.getSubject();
                String cartId = claims.get("cartId", String.class);

                ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                        .header("Authorization", "Bearer " + token.get())
                        .header("email", email)
                        .header("cart_id", cartId)
                        .header("roles", rolesString)
                        .build();

                return chain.filter(exchange.mutate().request(mutatedRequest).build());
            } catch (JwtException e) {
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token"));
            }
        };
    }
}
