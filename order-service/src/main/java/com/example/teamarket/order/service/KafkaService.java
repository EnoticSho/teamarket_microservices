package com.example.teamarket.order.service;

import com.example.teamarket.order.dto.request.payment.PaymentRequest;
import com.example.teamarket.order.dto.response.OrderInfoDto;
import com.example.teamarket.order.dto.response.PaymentInfoDto;
import com.example.teamarket.order.entities.Order;

public interface KafkaService {

    void handleNotification(PaymentInfoDto paymentInfoDto);

    void sendNotification(OrderInfoDto orderInfoDto);

    void sendPaymentInfo(PaymentRequest paymentRequest);
}
