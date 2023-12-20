package com.example.teamarket.cart.service.impl;

import com.example.teamarket.cart.dto.CartDto;
import com.example.teamarket.cart.dto.InfoProductDto;
import com.example.teamarket.cart.exception.ResourceNotFoundException;
import com.example.teamarket.cart.integration.ProductServiceIntegration;
import com.example.teamarket.cart.mapper.CartMapper;
import com.example.teamarket.cart.model.Cart;
import com.example.teamarket.cart.service.CartService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductServiceIntegration productServiceIntegration;
    private final CartMapper cartMapper = CartMapper.INSTANCE;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    @Override
    public CartDto getCurrentCart() {
        return cartMapper.cartToCartDto(tempCart);
    }

    @Override
    public void addItemToCart(Long productId) {
        InfoProductDto infoProductDto = productServiceIntegration.getProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт с id: " + productId));
        tempCart.addItem(infoProductDto);
    }

    @Override
    public void removeItem(Long productId) {
        tempCart.deleteItemById(productId);
    }

    @Override
    public void clear() {
        tempCart.deleteAllItems();
    }
}
