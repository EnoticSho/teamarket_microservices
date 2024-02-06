package com.example.teamarket.cart.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record InfoProductDto(
        Long productId,
        String name,
        BigDecimal price,
        List<String> imagesLinks) {
}
