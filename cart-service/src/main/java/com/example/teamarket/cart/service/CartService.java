package com.example.teamarket.cart.service;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.dto.response.StringResponse;

public interface CartService {
    CartDto getCurrentCart(String cartId);
    void addItemToCart(Long productId, int weight, String cartId);
    void removeItemFromCart(Long productId, String cartId);
    void clear(String cartId);
    StringResponse generateUuid();
    void editItem(String cartId, Long id, int weight);
}
