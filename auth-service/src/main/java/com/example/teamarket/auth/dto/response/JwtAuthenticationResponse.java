package com.example.teamarket.auth.dto.response;

public record JwtAuthenticationResponse(
        String accessToken,
        String refreshToken) {
}
