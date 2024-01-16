package com.example.teamarket.cart.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException of(Class<?> clazz, Object field) {
        return new ResourceNotFoundException(clazz.getSimpleName() + " with " + field + " not found!");
    }
}
