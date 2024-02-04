package com.example.teamarket.cart.exceptions;

public class ResourceEndedInStock extends RuntimeException{

    private ResourceEndedInStock(String message) {
        super(message);
    }

    public static ResourceEndedInStock of(Class<?> resourceType) {
        String message = resourceType.getSimpleName() + " : this quantity is not in stock";
        return new ResourceEndedInStock(message);
    }
}
