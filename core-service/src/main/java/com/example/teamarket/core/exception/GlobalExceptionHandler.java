package com.example.teamarket.core.exception;

import liquibase.exception.DatabaseException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles the exception when a resource is not found.
     *
     * @param ex The exception indicating the resource not found.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse();

        response.setErrorMessage(ex.getMessage());
        response.setErrorCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the exception when a database error occurs.
     *
     * @param ex The exception indicating a database error.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ApiErrorResponse> handleDatabaseException(DatabaseException ex) {
        ApiErrorResponse response = new ApiErrorResponse();

        response.setErrorMessage("Database Error: " + ex.getMessage());
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles the exception when a Hibernate error occurs.
     *
     * @param ex The exception indicating a Hibernate error.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ApiErrorResponse> handleHibernateError(PropertyValueException ex) {
        ApiErrorResponse response = new ApiErrorResponse();

        response.setErrorMessage("Hibernate Error: " + ex.getMessage());
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
