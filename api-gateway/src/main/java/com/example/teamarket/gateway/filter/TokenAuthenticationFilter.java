package com.example.teamarket.gateway.filter;

import com.example.teamarket.gateway.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class TokenAuthenticationFilter extends AbstractGatewayFilterFactory<TokenAuthenticationFilter.Config> {
    private final JwtUtil jwtUtil;

    public TokenAuthenticationFilter(JwtUtil jwtUtil) {
        super(TokenAuthenticationFilter.Config.class);
        this.jwtUtil = jwtUtil;
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header"));
            }
            String token = authorizationHeader.split(" ")[1].trim();
            if (jwtUtil.isInvalid(token)) {
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access token expired"));
            }
            try {
                String email = jwtUtil.getAllClaimsFromToken(token).get("sub", String.class);
                String cartId = jwtUtil.getAllClaimsFromToken(token).get("cartId", String.class);
                return chain.filter(exchange.mutate().request(exchange.getRequest().mutate()
                        .header("Authorization", "Bearer " + token)
                        .header("email", email)
                        .header("cart_id", cartId)
                        .build()).build());
            } catch (JwtException e) {
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token"));
            }
        };
    }
}
