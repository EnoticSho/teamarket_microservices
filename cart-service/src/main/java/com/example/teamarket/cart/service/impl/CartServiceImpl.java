package com.example.teamarket.cart.service.impl;

import com.example.teamarket.cart.dto.InfoProductDto;
import com.example.teamarket.cart.dto.StringResponse;
import com.example.teamarket.cart.integration.ProductServiceIntegration;
import com.example.teamarket.cart.mapper.CartMapper;
import com.example.teamarket.cart.model.Cart;
import com.example.teamarket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductServiceIntegration productServiceIntegration;

    private final RedisTemplate<String, Object> redisTemplate;
    private final CartMapper cartMapper = CartMapper.INSTANCE;

    @Override
    public Cart getCurrentCart(String cartUuid) {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(cartUuid))) {
            redisTemplate.opsForValue().set(cartUuid, new Cart());
        }

        return (Cart) redisTemplate.opsForValue().get(cartUuid);
    }

    @Override
    public void addItemToCart(Long productId, String uuid) {
        InfoProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid, cart -> cart.addItem(product));
    }

    @Override
    public void removeItemFromCart(Long id, String uuid) {
        execute(uuid, cart -> cart.deleteItemById(id));
    }

    @Override
    public void clear(String uuid) {
        execute(uuid, Cart::deleteAllItems);
    }

    @Override
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    private void execute(String uuid, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(uuid, cart);
    }
}
