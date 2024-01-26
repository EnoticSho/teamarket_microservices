package com.example.teamarket.order.dto.response.cart;

import java.math.BigDecimal;
import java.util.Map;


public record CartDto(
        Map<Long, CartItemDto> itemsMap,
        BigDecimal totalCost) {
}
