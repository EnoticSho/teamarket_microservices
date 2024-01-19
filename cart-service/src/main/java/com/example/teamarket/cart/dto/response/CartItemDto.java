package com.example.teamarket.cart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private String title;
    private Integer quantity;
    private BigDecimal pricePer;
    private BigDecimal subPrice;
}
