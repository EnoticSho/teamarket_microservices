package com.example.teamarket.notification.sevice;

import com.example.teamarket.notification.event.OrderInfoDto;

public interface KafkaService {
    void handleNotification(OrderInfoDto orderInfoDto);
}
