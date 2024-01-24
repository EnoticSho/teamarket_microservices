package com.example.teamarket.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Class representing an API error response.
 */
@Getter
@Setter
public class ApiErrorResponse {
    /**
     * The error message describing the issue.
     */
    private String errorMessage;

    /**
     * The error code associated with the issue.
     */
    private int errorCode;
}
