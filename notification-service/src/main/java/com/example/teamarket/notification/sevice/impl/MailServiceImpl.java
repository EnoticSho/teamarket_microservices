package com.example.teamarket.notification.sevice.impl;

import com.example.teamarket.notification.sevice.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String setFrom;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmail(String email, String subject, String content) {
        System.out.println(setFrom);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(setFrom);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        System.out.println(email);
        mailSender.send(message);
        System.out.println(3);
    }
}
