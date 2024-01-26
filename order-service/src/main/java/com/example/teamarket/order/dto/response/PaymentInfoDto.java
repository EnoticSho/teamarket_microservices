package com.example.teamarket.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record PaymentInfoDto (
    Long id,
    Long orderId,
    String email,
    BigDecimal total,
    String status,
    Timestamp created) {
}
