package com.example.teamarket.notification.sevice;

import com.example.teamarket.notification.event.OrderInfoDto;

public interface MailService {
    public void sendEmail(String email, String subject, OrderInfoDto orderInfoDto);
}
