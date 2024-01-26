package com.example.teamarket.order.service.impl;

import com.example.teamarket.order.dto.request.payment.CardInfo;
import com.example.teamarket.order.dto.request.payment.PaymentRequest;
import com.example.teamarket.order.dto.response.OrderInfoDto;
import com.example.teamarket.order.dto.response.PaymentInfoDto;
import com.example.teamarket.order.dto.response.cart.CartDto;
import com.example.teamarket.order.dto.response.cart.CartItemDto;
import com.example.teamarket.order.entities.Order;
import com.example.teamarket.order.entities.OrderItem;
import com.example.teamarket.order.entities.OrderType;
import com.example.teamarket.order.exception.ResourceNotFoundException;
import com.example.teamarket.order.integration.CartServiceIntegration;
import com.example.teamarket.order.mapper.OrderMapper;
import com.example.teamarket.order.repository.OrderRepository;
import com.example.teamarket.order.service.KafkaService;
import com.example.teamarket.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Implementation of the OrderService interface for managing orders and payments.
 */
@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final KafkaService kafkaService;
    private final OrderMapper orderMapper;

    /**
     * Saves an order based on the contents of a user's cart.
     *
     * @param cartId The cart identifier.
     * @param email  The user's email address.
     * @return The identifier of the saved order.
     */
    @Override
    public Long saveOrder(String cartId, String email) {
        CartDto cartDto = cartServiceIntegration.getCartById(cartId);
        Order order = new Order();
        order.setUserEmail(email);
        order.setOrderDate(Timestamp.from(Instant.now()));
        order.setStatus(OrderType.REGISTERED);
        order.setTotalPrice(cartDto.totalCost());

        List<OrderItem> orderItems = cartDto.itemsMap().values().stream()
                .map(item -> createOrderItem(item, order))
                .toList();

        order.setItemList(orderItems);
        Order savedOrder = orderRepository.save(order);
        kafkaService.sendNotification(orderMapper.entityToInfoDto(savedOrder));
        return savedOrder.getOrderId();
    }

    /**
     * Sends a payment request for the specified order.
     *
     * @param orderId  The identifier of the order.
     * @param cardInfo The card information for payment.
     */
    @Override
    public void sendPaymentRequest(Long orderId, CardInfo cardInfo) {
        OrderInfoDto orderById = findOrderById(orderId);
        PaymentRequest paymentRequest = new PaymentRequest(
                orderId,
                orderById.userEmail(),
                orderById.totalPrice(),
                cardInfo);

        kafkaService.sendPaymentRequest(paymentRequest);
    }

    /**
     * Retrieves an order by its identifier.
     *
     * @param id The identifier of the order.
     * @return The order information.
     */
    @Override
    public OrderInfoDto findOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::entityToInfoDto)
                .orElseThrow(() -> ResourceNotFoundException.of(id, Order.class));
    }

    /**
     * Handles payment status changes received via Kafka messages.
     *
     * @param paymentInfo The payment information.
     */
    @Override
    @KafkaListener(topics = "paymentInfoTopic")
    public void changeOrderStatus(PaymentInfoDto paymentInfo) {
        if ("Completed".equalsIgnoreCase(paymentInfo.status())) {
            orderRepository.updateStatus(paymentInfo.orderId(), OrderType.PAID.name());
        }
    }

    private OrderItem createOrderItem(CartItemDto cartItemDto, Order order) {
        return OrderItem.builder()
                .productId(cartItemDto.id())
                .order(order)
                .quantity(cartItemDto.quantity())
                .price(cartItemDto.costByHundredGrams())
                .build();
    }
}
