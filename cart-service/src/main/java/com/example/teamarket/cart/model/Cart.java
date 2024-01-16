package com.example.teamarket.cart.model;

import com.example.teamarket.cart.dto.response.InfoProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@NoArgsConstructor
public class Cart implements Serializable {
    private final Map<Long, CartItem> itemsMap = new HashMap<>();
    private BigDecimal totalCost = BigDecimal.ZERO;

    public void addItem(InfoProductDto infoProductDto, int weight) {
        CartItem cartItem = itemsMap.get(infoProductDto.getProductId());
        if (cartItem != null) {
            cartItem.changeQuantity(weight);
        }
        else {
            cartItem = new CartItem();
            cartItem.setId(infoProductDto.getProductId());
            cartItem.setTitle(infoProductDto.getName());
            cartItem.setQuantity(weight);
            cartItem.setPricePer(infoProductDto.getPrice());
            cartItem.setSubPrice(infoProductDto.getPrice()
                    .multiply(BigDecimal.valueOf(weight).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)));
            itemsMap.put(infoProductDto.getProductId(), cartItem);
        }
        recalculate();
    }

    public void editCartItem(Long id, int weight) {
        CartItem cartItem = itemsMap.get(id);
        if (cartItem != null) {
            cartItem.changeQuantity(weight);
            if (cartItem.getQuantity() <= 0) {
                itemsMap.remove(id);
            }
            recalculate();
        }
    }

    public void removeItemById(Long id) {
        if (itemsMap.remove(id) != null) {
            recalculate();
        }
    }

    public void removeAllItems() {
        itemsMap.clear();
        totalCost = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalCost = itemsMap.values().stream()
                .map(CartItem::getSubPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
