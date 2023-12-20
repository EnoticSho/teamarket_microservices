package com.example.teamarket.cart.integration;

import com.example.teamarket.cart.dto.InfoProductDto;
import com.example.teamarket.cart.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

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
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден"))
                )
                .bodyToMono(InfoProductDto.class)
                .block();

    }
}
