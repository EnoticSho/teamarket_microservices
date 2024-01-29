package com.example.teamarket.order.dto.response.cart;

import java.math.BigDecimal;

public record CartItemDto(
        Long id,
        String name,
        Integer weight,
        BigDecimal costByHundredGrams,
        BigDecimal sum) {
}
