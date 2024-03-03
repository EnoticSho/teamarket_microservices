package com.example.teamarket.cart.integration;

import com.example.teamarket.cart.dto.request.ProductInfo;
import com.example.teamarket.cart.exceptions.ResourceEndedInStock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class InventoryServiceIntegration {

    private final WebClient integrationServiceWebClient;

    public InventoryServiceIntegration(@Qualifier("integrationServiceWebClient") WebClient integrationServiceWebClient) {
        this.integrationServiceWebClient = integrationServiceWebClient;
    }

    public Boolean reserveProduct(Long productId, int quantity) {
        return integrationServiceWebClient.patch()
                .uri("/v1/api/inventory/reserve/" + productId + "/" + quantity)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.BAD_REQUEST.value(),
                        clientResponse -> Mono.error(ResourceEndedInStock.of(ProductInfo.class))
                )
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
