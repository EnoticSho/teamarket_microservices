package com.example.teamarket.notification.sevice.impl;

import com.example.teamarket.notification.event.OrderInfoDto;
import com.example.teamarket.notification.sevice.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Implementation of the MailService interface that provides email sending functionality.
 */
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String setFrom;

    private final JavaMailSender mailSender;

    /**
     * Sends an email to the specified recipient with the given subject and content.
     *
     * @param email   The recipient's email address.
     * @param subject The subject of the email.
     * @param orderInfoDto orderInfoDto.
     */
    @Override
    public void sendEmail(String email, String subject, OrderInfoDto orderInfoDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(setFrom);
        message.setTo(email);
        message.setSubject(subject);
        String content = "Ваш заказ номер: " + orderInfoDto.getOrderId() +
                " " + orderInfoDto.getStatus() + '\'' +
                ", дата: " + orderInfoDto.getOrderDate() +
                ", итоговая стоимость: " + orderInfoDto.getTotalPrice();
        message.setText(content);
        mailSender.send(message);
    }
}
