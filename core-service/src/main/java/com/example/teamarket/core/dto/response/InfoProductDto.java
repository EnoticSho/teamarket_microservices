package com.example.teamarket.core.dto.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public record InfoProductDto (
        Long productId,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String effect,
        List<String> imagesLinks,
        String category,
        Timestamp created,
        Timestamp updated) {

}
