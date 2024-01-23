package com.example.teamarket.order.dto.response;

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
public class OrderInfoDto {

    private Long orderId;
    private String userEmail;
    private Timestamp orderDate;
    private String status;
    private BigDecimal totalPrice;
}
