package com.example.teamarket.cart.dto.response;

import java.math.BigDecimal;

public record CartItemDto(
        Long id,
        String name,
        Integer weight,
        BigDecimal costByHundredGrams,
        BigDecimal sum) {
}
