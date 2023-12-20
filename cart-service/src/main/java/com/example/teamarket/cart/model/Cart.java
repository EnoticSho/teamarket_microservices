package com.example.teamarket.cart.model;

import com.example.teamarket.cart.dto.InfoProductDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ToString
@Component
@Getter
@Setter
public class Cart implements Serializable {
    private final List<CartItem> itemList;
    private BigDecimal totalCost;

    public Cart() {
        this.itemList = new ArrayList<>();
        this.totalCost = new BigDecimal(0);
    }

    private void recalculate() {
        totalCost = BigDecimal.ZERO;
        for (CartItem cartItem : itemList) {
            totalCost = totalCost.add(cartItem.getSubPrice());
        }
    }

    public void addItem(InfoProductDto infoProductDto) {
        for (CartItem cartItem : itemList) {
            if (cartItem.getId().equals(infoProductDto.getProductId())) {
                cartItem.changeQuantity(1);
                recalculate();
                return;
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(infoProductDto.getProductId());
        cartItem.setTitle(infoProductDto.getName());
        cartItem.setQuantity(1);
        cartItem.setPricePer(infoProductDto.getPrice());
        cartItem.setSubPrice(infoProductDto.getPrice());
        itemList.add(cartItem);
        totalCost = totalCost.add(cartItem.getSubPrice());
    }

    public void editCartItem(Long id, int inc) {
        for (CartItem cartItem : itemList) {
            if (cartItem.getId().equals(id)) {
                cartItem.changeQuantity(inc);
                if (cartItem.getQuantity() == 0) {
                    itemList.remove(cartItem);
                    recalculate();
                    break;
                }
                recalculate();
            }
        }
    }

    public void deleteItemById(Long id) {
        itemList.removeIf(item -> item.getId().equals(id));
        recalculate();
    }

    public void deleteAllItems() {
        itemList.clear();
        totalCost = BigDecimal.ZERO;
    }
}
