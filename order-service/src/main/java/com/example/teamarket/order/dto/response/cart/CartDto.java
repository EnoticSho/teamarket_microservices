package com.example.teamarket.order.dto.response.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Map<Long, CartItemDto> itemsMap;
    private BigDecimal totalCost;
}
