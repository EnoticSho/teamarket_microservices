package com.example.teamarket.order.dto.request.payment;

import java.math.BigDecimal;


public record PaymentRequest(
        Long orderId,
        String email,
        BigDecimal total,
        CardInfo cardInfo) {
}
