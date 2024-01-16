package com.example.teamarket.core.dto.request;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String effect;
    private List<String> imagesLinks;
    private String category;
}
