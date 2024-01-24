package com.example.teamarket.cart.integration;

import com.example.teamarket.cart.dto.response.InfoProductDto;
import com.example.teamarket.cart.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Integration class responsible for interacting with the ProductService to retrieve product information.
 */
@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final WebClient productServiceWebClient;

    /**
     * Retrieves product information by its ID from the ProductService.
     *
     * @param id The unique identifier of the product to retrieve.
     * @return An InfoProductDto representing the product information.
     * @throws ResourceNotFoundException If the product with the given ID is not found.
     */
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
