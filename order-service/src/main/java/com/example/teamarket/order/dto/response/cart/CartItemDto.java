package com.example.teamarket.order.dto.response.cart;

import java.math.BigDecimal;

public record CartItemDto(
        Long id,
        String title,
        Integer quantity,
        BigDecimal costByHundredGrams,
        BigDecimal amount) {
}
