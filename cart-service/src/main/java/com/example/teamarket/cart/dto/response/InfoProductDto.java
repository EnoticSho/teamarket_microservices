package com.example.teamarket.cart.dto.response;

import java.math.BigDecimal;

public record InfoProductDto(
        Long productId,
        String name,
        BigDecimal price) {
}
