package com.example.teamarket.cart.dto.response;

import com.example.teamarket.cart.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Map<Long, CartItemDto> itemsMap;
    private BigDecimal totalCost;
}
