package com.example.teamarket.cart.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InfoProductDto {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String effect;
    private List<String> imagesLinks;
    private String category;
}
