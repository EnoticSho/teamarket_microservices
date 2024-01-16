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
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse();

        response.setErrorMessage(ex.getMessage());
        response.setErrorCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ApiErrorResponse> handleDatabaseException(DatabaseException ex) {
        ApiErrorResponse response = new ApiErrorResponse();

        response.setErrorMessage("Ошибка базы данных: " + ex.getMessage());
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ApiErrorResponse> handleHibernateError(PropertyValueException ex) {
        ApiErrorResponse response = new ApiErrorResponse();

        response.setErrorMessage("Ошибка в Hibernate: " + ex.getMessage());
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
