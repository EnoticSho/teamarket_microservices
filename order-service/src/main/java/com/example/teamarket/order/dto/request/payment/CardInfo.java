package com.example.teamarket.order.dto.request.payment;

public record CardInfo(
        String cardNumber,
        String cardValidity,
        Integer CVV) {
}
