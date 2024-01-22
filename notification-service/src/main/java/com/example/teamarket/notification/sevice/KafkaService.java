package com.example.teamarket.notification.sevice;

import com.example.teamarket.notification.event.OrderPlacedEvent;

public interface KafkaService {
    void handleNotification(OrderPlacedEvent orderPlacedEvent);
}
