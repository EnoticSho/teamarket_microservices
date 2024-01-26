package com.example.teamarket.cart.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CartUtils {

    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final int SCALE = 2;

    private CartUtils() {}

    public static BigDecimal calculateAmount(BigDecimal price, int weight) {
        return price.multiply(BigDecimal.valueOf(weight).divide(HUNDRED, SCALE, RoundingMode.HALF_UP));
    }
}
