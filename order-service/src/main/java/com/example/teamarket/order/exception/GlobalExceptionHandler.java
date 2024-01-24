package com.example.teamarket.order.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling custom exceptions in the API.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles the ResourceNotFoundException and returns an appropriate error response.
     *
     * @param ex The ResourceNotFoundException to handle.
     * @return A ResponseEntity containing an ApiErrorResponse with error details and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse();

        response.setErrorMessage(ex.getMessage());
        response.setErrorCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
