package com.example.teamarket.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfoDto {
    private Long id;
    private Long orderId;
    private String email;
    private BigDecimal total;
    private String status;
    private Timestamp created;
}
