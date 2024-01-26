package com.example.teamarket.core.dto.response;

import java.util.List;

public record InfoCategoryDto(
        String name,
        String description,
        List<String> productList) {
}
