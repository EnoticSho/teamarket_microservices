package com.example.teamarket.core.dto.response;

import com.example.teamarket.core.entity.Product;

import java.util.List;

public record InfoCategoryDto(
        String name,
        String description,
        List<InfoProductDto> productList) {
}
