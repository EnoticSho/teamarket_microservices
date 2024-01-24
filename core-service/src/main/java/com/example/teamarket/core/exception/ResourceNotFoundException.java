package com.example.teamarket.core.exception;

/**
 * Exception thrown when a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructs a new ResourceNotFoundException with the given error message.
     *
     * @param message The error message.
     */
    private ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of ResourceNotFoundException with a formatted message.
     *
     * @param resourceId   The ID of the resource that was not found.
     * @param resourceType The type of the resource.
     * @return A ResourceNotFoundException instance.
     */
    public static ResourceNotFoundException of(Object resourceId, Class<?> resourceType) {
        String message = String.format("%s with id: %s not found", resourceType.getSimpleName(), resourceId);
        return new ResourceNotFoundException(message);
    }
}
