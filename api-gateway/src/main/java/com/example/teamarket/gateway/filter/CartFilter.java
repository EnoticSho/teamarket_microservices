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

@Component
public class CartFilter extends AbstractGatewayFilterFactory<CartFilter.Config> {

    private final JwtUtil jwtUtil;

    public CartFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

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

                    String cartId = jwtUtil.getAllClaimsFromToken(token.get()).get("cartId", String.class);
                    return chain.filter(exchange.mutate().request(request.mutate()
                            .header("cart_id", cartId)
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

    public static class Config {
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
