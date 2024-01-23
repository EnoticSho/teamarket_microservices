package com.example.teamarket.order.service.impl;

import com.example.teamarket.order.dto.request.payment.PaymentRequest;
import com.example.teamarket.order.dto.response.OrderInfoDto;
import com.example.teamarket.order.dto.response.PaymentInfoDto;
import com.example.teamarket.order.entities.Order;
import com.example.teamarket.order.mapper.OrderMapper;
import com.example.teamarket.order.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    @KafkaListener(topics = "paymentInfoTopic")
    public void handleNotification(PaymentInfoDto paymentInfoDto) {
        System.out.println(paymentInfoDto);
    }

    @Override
    public void sendNotification(OrderInfoDto orderInfoDto) {
        kafkaTemplate.send("notificationTopic", orderInfoDto);
    }

    public void sendPaymentInfo(PaymentRequest paymentRequest) {
        kafkaTemplate.send("paymentRequestTopic", paymentRequest);
    }
}
