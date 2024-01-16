package com.example.teamarket.auth.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException of(Object resourceId, Class<?> resourceType) {
        String message = String.format("%s with email: %s not found", resourceType.getSimpleName(), resourceId);
        return new ResourceNotFoundException(message);
    }
}
