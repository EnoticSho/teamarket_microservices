package com.example.teamarket.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable {
    private Long id;
    private String title;
    private int quantity;
    private BigDecimal pricePer;
    private BigDecimal subPrice;

    public void changeQuantity(int inc) {
        quantity = quantity + inc;
        subPrice = pricePer.multiply(BigDecimal.valueOf(quantity));
    }
}
