package com.example.teamarket.order.dto.response;

import java.math.BigDecimal;
import java.sql.Timestamp;


public record OrderInfoDto(
        Long orderId,
        String userEmail,
        Timestamp orderDate,
        String status,
        BigDecimal totalPrice) {
}
