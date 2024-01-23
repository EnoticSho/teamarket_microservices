package com.example.teamarket.notification.sevice.impl;

import com.example.teamarket.notification.event.OrderInfoDto;
import com.example.teamarket.notification.sevice.KafkaService;
import com.example.teamarket.notification.sevice.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final MailService mailService;

    @Override
    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderInfoDto orderInfoDto) {
        mailService.sendEmail(orderInfoDto.getUserEmail(), orderInfoDto.getStatus(), orderInfoDto.toString());
    }
}
