package com.example.teamarket.notification.sevice;

public interface MailService {
    public void sendEmail(String email, String subject, String content);
}
