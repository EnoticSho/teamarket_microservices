package com.example.teamarket.order.dto.request.cart;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CartItemDto {
    private Long id;
    private String title;
    private Integer quantity;
    private BigDecimal pricePer;
    private BigDecimal subPrice;
}
