package com.example.teamarket.order.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CartDto {
    private Map<Long, CartItemDto> itemsMap;
    private BigDecimal totalCost;
}
