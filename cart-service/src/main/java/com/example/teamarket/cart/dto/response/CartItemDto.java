package com.example.teamarket.cart.dto.response;

import java.math.BigDecimal;

public record CartItemDto(
        Long id,
        String title,
        Integer quantity,
        BigDecimal costByHundredGrams,
        BigDecimal amount) {
}
