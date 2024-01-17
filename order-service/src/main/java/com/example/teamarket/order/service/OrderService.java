package com.example.teamarket.order.service;

import com.example.teamarket.order.dto.response.CartDto;
import com.example.teamarket.order.dto.response.InfoUserDto;
import com.example.teamarket.order.entities.Order;
import com.example.teamarket.order.entities.OrderItem;
import com.example.teamarket.order.integration.CartServiceIntegration;
import com.example.teamarket.order.integration.UserServiceIntegration;
import com.example.teamarket.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final UserServiceIntegration userServiceIntegration;

    public Long saveOrder(String cartId, String email) {
        CartDto cartDto = cartServiceIntegration.getCartById(cartId);
        InfoUserDto userByEmail = userServiceIntegration.getUserByEmail(email);
        System.out.println(userByEmail);
        Order order = new Order();
        order.setUserId(userByEmail.getId());
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
