package com.example.teamarket.order.exception;

/**
 * A custom exception class representing a resource not found error.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructs a ResourceNotFoundException with the specified error message.
     *
     * @param message The error message describing the resource not found error.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates and returns a ResourceNotFoundException with a formatted error message.
     *
     * @param resourceId   The ID of the resource that was not found.
     * @param resourceType The class type of the resource.
     * @return A ResourceNotFoundException with a formatted error message.
     */
    public static ResourceNotFoundException of(Object resourceId, Class<?> resourceType) {
        String message = String.format("%s with id: %s not found", resourceType.getSimpleName(), resourceId);
        return new ResourceNotFoundException(message);
    }
}
