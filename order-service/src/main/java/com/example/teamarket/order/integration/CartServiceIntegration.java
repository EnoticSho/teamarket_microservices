package com.example.teamarket.order.integration;

import com.example.teamarket.order.dto.response.CartDto;
import com.example.teamarket.order.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;

    public CartDto getCartByEmail(String cartId) {
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
