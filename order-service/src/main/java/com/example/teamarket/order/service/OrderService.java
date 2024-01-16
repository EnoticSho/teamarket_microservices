package com.example.teamarket.order.service;

import com.example.teamarket.order.dto.response.CartDto;
import com.example.teamarket.order.entities.Order;
import com.example.teamarket.order.entities.OrderItem;
import com.example.teamarket.order.integration.CartServiceIntegration;
import com.example.teamarket.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public Long saveOrder(String email) {
        CartDto cartDto = cartServiceIntegration.getCartByEmail(email);
        Order order = new Order();
        order.setUserId(1L);
        order.setOrderDate(Timestamp.from(Instant.now()));
        order.setStatus("Зарегистрирован");
        order.setTotalPrice(cartDto.getTotalCost());
        List<OrderItem> orderItems = cartDto.getItemsMap().values().stream()
                .map(cartItemDto -> OrderItem.builder()
                        .productId(cartItemDto.getId())
                        .order(order)
                        .quantity(cartItemDto.getQuantity())
                        .price(cartItemDto.getSubPrice())
                        .build())
                .toList();
        order.setItemList(orderItems);
        return orderRepository.save(order).getOrderId();
    }
}
