package com.example.teamarket.order.service.impl;

import com.example.teamarket.order.dto.request.CartDto;
import com.example.teamarket.order.dto.response.OrderInfoDto;
import com.example.teamarket.order.entities.Order;
import com.example.teamarket.order.entities.OrderItem;
import com.example.teamarket.order.entities.OrderType;
import com.example.teamarket.order.integration.CartServiceIntegration;
import com.example.teamarket.order.mapper.OrderMapper;
import com.example.teamarket.order.repository.OrderRepository;
import com.example.teamarket.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final OrderMapper orderMapper;
    private final KafkaTemplate<String, OrderInfoDto> kafkaTemplate;

    public Long saveOrder(String cartId, String email) {
        CartDto cartDto = cartServiceIntegration.getCartById(cartId);
        Order order = new Order();
        order.setUserEmail(email);
        order.setOrderDate(Timestamp.from(Instant.now()));
        order.setStatus(OrderType.REGISTERED);
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
        Order save = orderRepository.save(order);
        kafkaTemplate.send("notificationTopic", orderMapper.entityToInfoDto(save));
        return save.getOrderId();
    }
}
