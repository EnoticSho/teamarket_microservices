package com.example.teamarket.order.service.impl;

import com.example.teamarket.order.dto.response.PaymentInfoDto;
import com.example.teamarket.order.dto.response.cart.CartDto;
import com.example.teamarket.order.dto.request.payment.CardInfo;
import com.example.teamarket.order.dto.request.payment.PaymentRequest;
import com.example.teamarket.order.dto.response.OrderInfoDto;
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
                        .price(cartItemDto.getCostByHundredGrams())
                        .build())
                .toList();
        order.setItemList(orderItems);
        Order save = orderRepository.save(order);
        kafkaService.sendNotification(orderMapper.entityToInfoDto(save));
        return save.getOrderId();
    }

    /**
     * Sends a payment request for the specified order.
     *
     * @param orderId   The identifier of the order.
     * @param cardInfo  The card information for payment.
     */
    @Override
    public void sendPaymentRequest(Long orderId, CardInfo cardInfo) {
        OrderInfoDto orderById = findOrderById(orderId);
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(orderId);
        paymentRequest.setEmail(orderById.getUserEmail());
        paymentRequest.setTotal(orderById.getTotalPrice());
        paymentRequest.setCardInfo(cardInfo);

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
     * @param paymentInfoDto The payment information.
     */
    @Override
    @KafkaListener(topics = "paymentInfoTopic")
    public void changeOrderStatus(PaymentInfoDto paymentInfoDto) {
        if (paymentInfoDto.getStatus().equalsIgnoreCase("Completed")) {
            orderRepository.updateStatus(paymentInfoDto.getOrderId(), String.valueOf(OrderType.PAID));
            System.out.println(findOrderById(paymentInfoDto.getOrderId()));
        }
    }
}
