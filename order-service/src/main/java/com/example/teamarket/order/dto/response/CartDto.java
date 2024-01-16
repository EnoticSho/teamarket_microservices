package com.example.teamarket.order.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartDto {
    private List<CartItemDto> itemList;
    private BigDecimal totalCost;
}
