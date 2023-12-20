package com.example.teamarket.cart.service;

import com.example.teamarket.cart.dto.CartDto;
import com.example.teamarket.cart.dto.InfoProductDto;
import com.example.teamarket.cart.model.Cart;

public interface CartService {
    CartDto getCurrentCart();
    void addItemToCart(Long productId);
    void removeItem(Long productId);
    void clear();
}
