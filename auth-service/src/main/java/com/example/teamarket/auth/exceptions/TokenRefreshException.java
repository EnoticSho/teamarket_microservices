package com.example.teamarket.auth.exceptions;

public class TokenRefreshException extends RuntimeException {

    public TokenRefreshException(String message) {
        super(message);
    }
}
