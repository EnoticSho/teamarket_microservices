package com.example.teamarket.payment.dto.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record PaymentInfoDto(
        Long id,
        Long orderId,
        String email,
        BigDecimal total,
        String status,
        Timestamp created) {
}
