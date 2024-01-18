package com.example.teamarket.core.integrations;

import com.example.teamarket.core.dto.response.ReviewInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewServiceIntegration {

    private final WebClient reviewServiceWebClient;

    public List<ReviewInfoDto> getProductById(Long productId) {
        return reviewServiceWebClient.get()
                .uri("/reviews/" + productId)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new Exception())
                )
                .bodyToMono(new ParameterizedTypeReference<List<ReviewInfoDto>>() {})
                .block();
    }
}
