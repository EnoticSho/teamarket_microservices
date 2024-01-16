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

    public CartDto getCartByEmail(String email) {
        return cartServiceWebClient.get()
                .uri("/v1/api/cart")
                .header("email", email) // установка заголовка email
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден"))
                )
                .bodyToMono(CartDto.class)
                .block();
    }
}
