package com.example.teamarket.cart.model;

import com.example.teamarket.cart.utils.CartUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable {
    private Long id;
    private String name;
    private Integer weight;
    private BigDecimal costByHundredGrams;
    private BigDecimal sum;

    public void changeQuantity(int weight) {
        this.weight += weight;
        sum = CartUtils.calculateAmount(costByHundredGrams, this.weight);
    }
}
