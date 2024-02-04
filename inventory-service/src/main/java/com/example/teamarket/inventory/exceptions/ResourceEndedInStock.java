package com.example.teamarket.inventory.exceptions;

public class ResourceEndedInStock extends RuntimeException{

    private ResourceEndedInStock(String message) {
        super(message);
    }

    public static ResourceEndedInStock of(Object object) {
        String message = object.getClass().getSimpleName() + " : this quantity is not in stock";
        return new ResourceEndedInStock(message);
    }
}
