package com.example.teamarket.order.service;

public interface OrderService {
    Long saveOrder(String cartId, String email);
}
