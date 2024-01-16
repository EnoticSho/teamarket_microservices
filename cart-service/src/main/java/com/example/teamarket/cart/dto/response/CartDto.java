package com.example.teamarket.cart.dto.response;

import com.example.teamarket.cart.model.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CartDto {
    private Map<Long, CartItem> itemsMap;
    private BigDecimal totalCost;
}
