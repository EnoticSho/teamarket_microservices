package com.example.teamarket.core.integrations;

import com.example.teamarket.core.dto.response.ReviewInfoDto;
import com.example.teamarket.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Integration class to interact with the Review Service.
 */
@Component
@RequiredArgsConstructor
public class ReviewServiceIntegration {

    private final WebClient reviewServiceWebClient;

    /**
     * Retrieves a list of reviews for a product by its ID.
     *
     * @param productId The ID of the product to retrieve reviews for.
     * @return A list of review information DTOs.
     * @throws ResourceNotFoundException If the product or reviews are not found.
     */
    public List<ReviewInfoDto> getProductReviewsById(Long productId) throws ResourceNotFoundException {
        return reviewServiceWebClient.get()
                .uri("/reviews/" + productId)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(ResourceNotFoundException.of(productId, ReviewInfoDto.class))
                )
                .bodyToMono(new ParameterizedTypeReference<List<ReviewInfoDto>>() {
                })
                .block();
    }
}
