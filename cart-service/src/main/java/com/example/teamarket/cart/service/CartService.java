package com.example.teamarket.cart.service;

import com.example.teamarket.cart.dto.StringResponse;
import com.example.teamarket.cart.model.Cart;

public interface CartService {
    Cart getCurrentCart(String uuid);
    void addItemToCart(Long productId, String uuid);
    void removeItemFromCart(Long productId, String uuid);
    void clear(String uuid);
    StringResponse generateUuid();
}
