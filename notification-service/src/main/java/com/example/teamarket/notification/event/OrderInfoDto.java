package com.example.teamarket.notification.event;

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

    @Override
    public String toString() {
        return "Ваш заказ номер: " + orderId +
                " " + status + '\'' +
                ", дата: " + orderDate +
                ", итоговая стоимость: " + totalPrice;
    }
}
