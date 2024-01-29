package com.example.teamarket.inventory.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * A DTO class for representing error responses in the API.
 */
@Getter
@Setter
public class ApiErrorResponse {
    /**
     * A message describing the error.
     */
    private String errorMessage;

    /**
     * An error code associated with the error.
     */
    private int errorCode;
}
