package com.example.teamarket.cart.dto.response;

import java.math.BigDecimal;
import java.util.Map;


public record CartDto(
        Map<Long, CartItemDto> itemsMap,
        BigDecimal totalCost) {
}
