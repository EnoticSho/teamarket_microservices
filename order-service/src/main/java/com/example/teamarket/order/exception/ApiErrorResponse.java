package com.example.teamarket.order.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * A class representing an error response in the API.
 */
@Getter
@Setter
public class ApiErrorResponse {
    private String errorMessage;
    private int errorCode;
}
