package com.example.teamarket.cart.service;

import com.example.teamarket.cart.dto.request.ProductInfo;
import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.dto.response.StringResponse;

public interface CartService {
    CartDto getCurrentCart(String cartId);
    void addItemToCart(ProductInfo productInfo, String cartId);
    void removeItemFromCart(Long productId, String cartId);
    void clear(String cartId);
    StringResponse generateUuid();
    void editItem(String cartId, Long id, ProductInfo productInfo);
}
