package com.example.teamarket.payment.dto.request;

import java.math.BigDecimal;

public record PaymentRequest(

        Long orderId,
        String email,
        BigDecimal total,
        CardInfo cardInfo) {
}
