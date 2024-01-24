package com.example.teamarket.order.service.impl;

import com.example.teamarket.order.dto.request.payment.PaymentRequest;
import com.example.teamarket.order.dto.response.OrderInfoDto;
import com.example.teamarket.order.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Implementation of the KafkaService interface for sending Kafka messages.
 */
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Sends a notification message to the "notificationTopic" Kafka topic.
     *
     * @param orderInfoDto The order information to send in the notification.
     */
    @Override
    public void sendNotification(OrderInfoDto orderInfoDto) {
        kafkaTemplate.send("notificationTopic", orderInfoDto);
    }

    /**
     * Sends a payment request message to the "paymentRequestTopic" Kafka topic.
     *
     * @param paymentRequest The payment request information to send.
     */
    public void sendPaymentRequest(PaymentRequest paymentRequest) {
        kafkaTemplate.send("paymentRequestTopic", paymentRequest);
    }
}
