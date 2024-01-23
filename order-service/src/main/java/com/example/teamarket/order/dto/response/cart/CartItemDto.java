package com.example.teamarket.order.dto.response.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private String title;
    private Integer quantity;
    private BigDecimal costByHundredGrams;
    private BigDecimal amount;
}
