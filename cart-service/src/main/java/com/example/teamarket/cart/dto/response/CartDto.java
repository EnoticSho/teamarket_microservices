package com.example.teamarket.cart.dto.response;

import java.math.BigDecimal;
import java.util.List;


public record CartDto(
        List<CartItemDto> itemsMap,
        BigDecimal totalCost) {
}
