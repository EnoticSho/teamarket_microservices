package com.example.teamarket.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable {
    private Long id;
    private String title;
    private Integer quantity;
    private BigDecimal pricePer;
    private BigDecimal subPrice;

    public void changeQuantity(int weight) {
        quantity = quantity + weight;
        subPrice = pricePer.multiply(BigDecimal.valueOf(weight)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
    }
}
