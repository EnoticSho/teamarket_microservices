package com.example.teamarket.order.service;

import com.example.teamarket.order.dto.request.payment.CardInfo;
import com.example.teamarket.order.dto.request.payment.PaymentRequest;
import com.example.teamarket.order.dto.response.OrderInfoDto;

public interface OrderService {
    Long saveOrder(String cartId, String email);
    void sendPaymentRequest(Long orderId, CardInfo cardInfo);
    OrderInfoDto findOrderById(Long id);
}
