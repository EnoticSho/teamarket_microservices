package com.example.teamarket.order.integration;

import com.example.teamarket.order.dto.response.InfoUserDto;
import com.example.teamarket.order.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserServiceIntegration {

    @Qualifier("userServiceWebClient")
    private WebClient userServiceWebClient;

    public InfoUserDto getUserByEmail(String email) {
        return userServiceWebClient.get()
                .uri("/v1/api/users")
                .header("email", email)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(ResourceNotFoundException.of(email, InfoUserDto.class))
                )
                .bodyToMono(InfoUserDto.class)
                .block();
    }
}
