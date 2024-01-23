package com.example.teamarket.order.service;

import com.example.teamarket.order.dto.request.payment.CardInfo;
import com.example.teamarket.order.dto.response.OrderInfoDto;
import com.example.teamarket.order.dto.response.PaymentInfoDto;

public interface OrderService {
    Long saveOrder(String cartId, String email);

    void sendPaymentRequest(Long orderId, CardInfo cardInfo);

    OrderInfoDto findOrderById(Long id);

    void changeOrderStatus(PaymentInfoDto paymentInfoDto);
}
