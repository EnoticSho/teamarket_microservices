package com.example.teamarket.payment.service.impl;

import com.example.teamarket.payment.dto.request.PaymentRequest;
import com.example.teamarket.payment.dto.response.PaymentInfoDto;
import com.example.teamarket.payment.entity.Payment;
import com.example.teamarket.payment.entity.PaymentStatus;
import com.example.teamarket.payment.mapper.PaymentMapper;
import com.example.teamarket.payment.repository.PaymentRepository;
import com.example.teamarket.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Implementation of the PaymentService interface for processing payments and sending payment responses.
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Processes a payment request.
     *
     * @param paymentRequest The payment request to process.
     */
    @Override
    @SneakyThrows
    @KafkaListener(topics = "paymentRequestTopic")
    public void processPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentMapper.dtoToEntity(paymentRequest);
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
        System.out.println(payment);
        payment.setStatus(PaymentStatus.COMPLETED);

        paymentRepository.save(payment);

        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));

        sendResponse(paymentMapper.entityInfoDto(payment));
    }

    /**
     * Sends a payment response to a Kafka topic.
     *
     * @param paymentInfoDto The payment response to send.
     */
    @Override
    public void sendResponse(PaymentInfoDto paymentInfoDto) {
        kafkaTemplate.send("paymentInfoTopic", paymentInfoDto);
    }
}
