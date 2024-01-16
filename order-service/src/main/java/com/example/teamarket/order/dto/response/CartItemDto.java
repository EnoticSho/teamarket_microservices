package com.example.teamarket.order.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CartItemDto {
    private Long id;
    private String title;
    private int quantity;
    private BigDecimal pricePer;
    private BigDecimal subPrice;
}