package com.example.teamarket.gateway.filter;

import com.example.teamarket.gateway.util.JwtTokenUtil;
import com.example.teamarket.gateway.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * This class represents a filter for managing shopping cart-related requests.
 */
@Component
public class CartFilter extends AbstractGatewayFilterFactory<CartFilter.Config> {

    private final JwtUtil jwtUtil;

    /**
     * Constructor for the CartFilter class.
     *
     * @param jwtUtil An instance of JwtUtil used for JWT operations.
     */
    public CartFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    /**
     * Applies the CartFilter to the given ServerWebExchange.
     *
     * @param config The configuration for the CartFilter.
     * @return A GatewayFilter that applies cart-related logic to the request.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            Optional<String> token = JwtTokenUtil.extractToken(request);

            if (token.isPresent()) {
                try {
                    if (jwtUtil.isInvalid(token.get())) {
                        return onError(exchange, "Access token expired");
                    }

                    String email = jwtUtil.getAllClaimsFromToken(token.get()).getSubject();
                    return chain.filter(exchange.mutate().request(request.mutate()
                            .header("cart_id", email)
                            .build()).build());
                } catch (JwtException e) {
                    return onError(exchange, "Invalid token");
                }
            }
            else if (StringUtils.hasText(request.getHeaders().getFirst("cart_id"))) {
                return chain.filter(exchange);
            }
            else {
                return onError(exchange, "Missing Authorization header");
            }
        };
    }

    /**
     * Configuration class for the CartFilter.
     */
    public static class Config {
    }

    /**
     * Handles errors in the filter and sets an unauthorized response status.
     *
     * @param exchange The ServerWebExchange instance.
     * @param err      The error message to be returned.
     * @return A Mono<Void> representing the error response.
     */
    private Mono<Void> onError(ServerWebExchange exchange, String err) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
