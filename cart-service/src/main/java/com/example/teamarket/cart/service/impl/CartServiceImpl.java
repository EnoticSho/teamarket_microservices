package com.example.teamarket.cart.service.impl;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.dto.response.InfoProductDto;
import com.example.teamarket.cart.dto.response.StringResponse;
import com.example.teamarket.cart.exceptions.ResourceNotFoundException;
import com.example.teamarket.cart.integration.InventoryServiceIntegration;
import com.example.teamarket.cart.integration.ProductServiceIntegration;
import com.example.teamarket.cart.mapper.CartMapper;
import com.example.teamarket.cart.model.Cart;
import com.example.teamarket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductServiceIntegration productServiceIntegration;
    private final InventoryServiceIntegration inventoryServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;
    private final CartMapper cartMapper;

    /**
     * Retrieves the current cart associated with the given UUID.
     *
     * @param cartUuid The UUID of the cart to retrieve.
     * @return The {@link CartDto} representing the current cart.
     * @throws ResourceNotFoundException if the cart is not found.
     */
    @Override
    public CartDto getCurrentCart(String cartUuid) {
        log.debug("Getting current cart with UUID: {}", cartUuid);
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartUuid);
        if (Objects.equals(cart, null)) {
            log.error("Cart not found with UUID: {}", cartUuid);
            throw ResourceNotFoundException.of(cartUuid, Cart.class);
        }
        return cartMapper.modelToDto(cart);
    }

    /**
     * Adds an item to the cart with the given product ID and weight.
     *
     * @param productId The ID of the product to add to the cart.
     * @param weight    The weight of the product to add to the cart.
     * @param uuid      The UUID of the cart.
     */
    @Override
    public void addItemToCart(Long productId, int weight, String uuid) {
        if (inventoryServiceIntegration.reserveProduct(productId, weight)) {
            InfoProductDto product = productServiceIntegration.getProductById(productId);
            execute(uuid, cart -> cart.addItem(product, weight));
        }
    }

    /**
     * Removes an item from the cart with the given ID.
     *
     * @param id   The ID of the item to remove from the cart.
     * @param uuid The UUID of the cart.
     */
    @Override
    public void removeItemFromCart(Long id, String uuid) {
        execute(uuid, cart -> cart.removeItemById(id));
    }

    /**
     * Clears the cart associated with the given UUID.
     *
     * @param uuid The UUID of the cart to clear.
     */
    @Override
    public void clear(String uuid) {
        execute(uuid, Cart::removeAllItems);
    }

    /**
     * Generates a UUID for a new cart and stores it in Redis.
     *
     * @return A {@link StringResponse} containing the generated UUID.
     */
    @Override
    public StringResponse generateUuid() {
        String cartUuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(cartUuid, new Cart());
        return new StringResponse(cartUuid);
    }

    /**
     * Edits the quantity of an item in the cart.
     *
     * @param cartId The UUID of the cart.
     * @param id     The ID of the item to edit.
     * @param weight The new weight of the item.
     */
    @Override
    public void editItem(String cartId, Long id, int weight) {
        if (weight > 0) {
            if (inventoryServiceIntegration.reserveProduct(id, weight)) {
                execute(cartId, cart -> cart.editCartItem(id, weight));
            }
        }
        else {
            inventoryServiceIntegration.returnProduct(id, weight);
            execute(cartId, cart -> cart.editCartItem(id, weight));
        }
    }


    private void execute(String cartUuid, Consumer<Cart> operation) {
        log.debug("Executing operation on cart with UUID: {}", cartUuid);
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartUuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartUuid, Objects.requireNonNull(cart));
        log.debug("Operation executed successfully on cart with UUID: {}", cartUuid);
    }
}
