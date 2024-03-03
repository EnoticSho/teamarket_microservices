package com.example.teamarket.order.integration;

import com.example.teamarket.order.dto.response.cart.CartDto;
import com.example.teamarket.order.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Integration service for communicating with the Cart service.
 */
@Component
public class CartServiceIntegration {

    @Qualifier("cartServiceWebClient")
    private WebClient cartServiceWebClient;


    /**
     * Retrieves the cart information by its ID from the Cart service.
     *
     * @param cartId The ID of the cart to retrieve.
     * @return The CartDto containing cart information.
     * @throws ResourceNotFoundException If the cart is not found, a ResourceNotFoundException is thrown.
     */
    public CartDto getCartById(String cartId) {
        return cartServiceWebClient.get()
                .uri("/v1/api/cart")
                .header("cart_id", cartId)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(ResourceNotFoundException.of(cartId, CartDto.class))
                )
                .bodyToMono(CartDto.class)
                .block();
    }
}
