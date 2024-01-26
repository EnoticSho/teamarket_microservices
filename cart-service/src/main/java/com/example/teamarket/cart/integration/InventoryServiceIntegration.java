package com.example.teamarket.cart.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class InventoryServiceIntegration {

    private final WebClient integrationServiceWebClient;

    public Boolean reserveProduct(Long productId, int quantity) {
        return integrationServiceWebClient.patch()
                .uri("/v1/api/inventory/reserve/" + productId + "/" + quantity)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new RuntimeException("Не удалось проверить наличие продукта"))
                )
                .bodyToMono(Boolean.class)
                .block();
    }

    public void returnProduct(Long productId, int quantity) {
        integrationServiceWebClient.patch()
                .uri("/v1/api/inventory/return/" + productId + "/" + quantity)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
