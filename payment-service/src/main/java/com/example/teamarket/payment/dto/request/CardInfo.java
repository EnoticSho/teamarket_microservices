package com.example.teamarket.payment.dto.request;

public record CardInfo (
    String cardNumber,
    String cardValidity,
    Integer CVV) {
}
