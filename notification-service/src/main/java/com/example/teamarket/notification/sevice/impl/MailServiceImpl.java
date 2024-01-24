package com.example.teamarket.notification.sevice.impl;

import com.example.teamarket.notification.sevice.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Implementation of the MailService interface that provides email sending functionality.
 */
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String setFrom;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends an email to the specified recipient with the given subject and content.
     *
     * @param email   The recipient's email address.
     * @param subject The subject of the email.
     * @param content The content of the email.
     */
    @Override
    public void sendEmail(String email, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(setFrom);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
