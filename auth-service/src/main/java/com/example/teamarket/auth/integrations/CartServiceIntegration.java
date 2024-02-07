package com.example.teamarket.auth.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;

    public Mono<ResponseEntity<Void>> mergeCarts(String cartId, String email) {
        return cartServiceWebClient.post()
                .uri("/cart/mergeCart")
                .header("cart_id", cartId)
                .header("email", email)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> Mono.empty())
                .toBodilessEntity()
                .thenReturn(ResponseEntity.ok().build());
    }
}
