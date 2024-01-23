package com.example.teamarket.order.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long userId;
    private Timestamp orderDate;
    private String status;
    private BigDecimal totalPrice;
    private List<OrderItemDto> itemList;
}
