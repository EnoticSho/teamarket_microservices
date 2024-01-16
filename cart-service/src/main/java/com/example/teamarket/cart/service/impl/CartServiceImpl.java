package com.example.teamarket.cart.service.impl;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.dto.response.InfoProductDto;
import com.example.teamarket.cart.dto.response.StringResponse;
import com.example.teamarket.cart.integration.ProductServiceIntegration;
import com.example.teamarket.cart.mapper.CartMapper;
import com.example.teamarket.cart.model.Cart;
import com.example.teamarket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductServiceIntegration productServiceIntegration;

    private final RedisTemplate<String, Object> redisTemplate;
    private final CartMapper cartMapper = CartMapper.INSTANCE;

    @Override
    public CartDto getCurrentCart(String cartUuid) {
        return cartMapper.cartToCartDto((Cart) redisTemplate.opsForValue().get(cartUuid));
    }

    @Override
    public void addItemToCart(Long productId, int weight, String uuid) {
        InfoProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid, cart -> cart.addItem(product, weight));
    }

    @Override
    public void removeItemFromCart(Long id, String uuid) {
        execute(uuid, cart -> cart.removeItemById(id));
    }

    @Override
    public void clear(String uuid) {
        execute(uuid, Cart::removeAllItems);
    }

    @Override
    public StringResponse generateUuid() {
        String cartUuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(cartUuid, new Cart());
        return new StringResponse(cartUuid);
    }

    @Override
    public void editItem(String cartId, Long id, int weight) {
            execute(cartId, cart -> cart.editCartItem(id, weight));
    }

    private void execute(String cartUuid, Consumer<Cart> operation) {
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartUuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartUuid, Objects.requireNonNull(cart));
    }
}
