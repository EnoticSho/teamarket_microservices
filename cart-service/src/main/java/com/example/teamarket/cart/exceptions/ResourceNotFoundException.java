package com.example.teamarket.cart.exceptions;

/**
 * Exception indicating that a requested resource was not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the given error message.
     *
     * @param message The error message explaining the resource not found situation.
     */
    private ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a ResourceNotFoundException with a message indicating that a resource of a specific type with a given identifier was not found.
     *
     * @param resourceId   The identifier of the resource.
     * @param resourceType The type or class of the resource.
     * @return A ResourceNotFoundException with a formatted error message.
     */
    public static ResourceNotFoundException of(Object resourceId, Class<?> resourceType) {
        String message = String.format("%s with identifier: %s not found", resourceType.getSimpleName(), resourceId);
        return new ResourceNotFoundException(message);
    }
}
