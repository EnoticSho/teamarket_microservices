package com.example.teamarket.reviews.dto.request;

public record ReviewDto(
        Long productId,
        String userEmail,
        Integer rating,
        String comment) {
}
