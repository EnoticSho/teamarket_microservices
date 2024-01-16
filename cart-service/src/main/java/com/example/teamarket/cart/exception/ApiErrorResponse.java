package com.example.teamarket.cart.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {
    private String errorMessage;
    private int errorCode;
}
