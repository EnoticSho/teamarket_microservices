package com.example.teamarket.order.dto.request;

import com.example.teamarket.order.entities.Order;
import com.example.teamarket.order.entities.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double price;
}
