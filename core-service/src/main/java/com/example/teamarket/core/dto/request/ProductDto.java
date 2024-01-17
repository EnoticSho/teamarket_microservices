package com.example.teamarket.core.dto.request;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String effect;
    private List<String> imagesLinks;
    private String category;
}
