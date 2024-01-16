package com.example.teamarket.cart.integration;

import com.example.teamarket.cart.dto.response.InfoProductDto;
import com.example.teamarket.cart.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final WebClient productServiceWebClient;

    public InfoProductDto getProductById(Long id) {
        return productServiceWebClient.get()
                .uri("/v1/api/products/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(ResourceNotFoundException.of(id, InfoProductDto.class))
                )
                .bodyToMono(InfoProductDto.class)
                .block();
    }
}
