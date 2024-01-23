package com.example.teamarket.order.service;

import com.example.teamarket.order.dto.request.payment.PaymentRequest;
import com.example.teamarket.order.dto.response.OrderInfoDto;

public interface KafkaService {

    void sendNotification(OrderInfoDto orderInfoDto);

    void sendPaymentRequest(PaymentRequest paymentRequest);
}
