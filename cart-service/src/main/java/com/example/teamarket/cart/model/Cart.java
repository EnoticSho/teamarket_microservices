package com.example.teamarket.cart.model;

import com.example.teamarket.cart.dto.response.InfoProductDto;
import com.example.teamarket.cart.exceptions.ResourceNotFoundException;
import com.example.teamarket.cart.utils.CartUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@NoArgsConstructor
public class Cart implements Serializable {

    private final Map<Long, CartItem> itemsMap = new HashMap<>();
    private BigDecimal totalCost = BigDecimal.ZERO;

    public void addItem(InfoProductDto infoProductDto, int weight) {
        CartItem cartItem = itemsMap.get(infoProductDto.productId());
        if (cartItem != null) {
            cartItem.changeQuantity(weight);
        }
        else {
            CartItem build = CartItem.builder()
                    .id(infoProductDto.productId())
                    .name(infoProductDto.name())
                    .weight(weight)
                    .costByHundredGrams(infoProductDto.price())
                    .sum(CartUtils.calculateAmount(infoProductDto.price(), weight))
                    .build();
            itemsMap.put(infoProductDto.productId(), build);
        }
        recalculateTotalCost();
    }

    public void editCartItem(Long id, int weight) {
        CartItem cartItem = itemsMap.get(id);
        if (cartItem != null) {
            cartItem.changeQuantity(weight);
            removeItemIfNecessary(id, cartItem);
            recalculateTotalCost();
        }
        else {
            throw ResourceNotFoundException.of(id, CartItem.class);
        }
    }

    public void removeItemById(Long id) {
        if (itemsMap.remove(id) != null) {
            recalculateTotalCost();
        }
    }

    public void removeAllItems() {
        itemsMap.clear();
        totalCost = BigDecimal.ZERO;
    }

    private void recalculateTotalCost() {
        totalCost = itemsMap.values().stream()
                .map(CartItem::getSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void removeItemIfNecessary(Long id, CartItem cartItem) {
        if (cartItem.getWeight() <= 0) {
            itemsMap.remove(id);
        }
    }
}
