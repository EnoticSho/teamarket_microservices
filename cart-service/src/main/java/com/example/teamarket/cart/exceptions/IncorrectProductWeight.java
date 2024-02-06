package com.example.teamarket.cart.exceptions;

public class IncorrectProductWeight extends RuntimeException {

    private IncorrectProductWeight(String message) {
        super(message);
    }

    public static IncorrectProductWeight of(Long productId, Integer cartWeight, Integer requestWeight) {
        String message = "Weight of product with id: " + productId + " in cart: " + cartWeight + "but request weight: " + requestWeight;
        return new IncorrectProductWeight(message);
    }
}
