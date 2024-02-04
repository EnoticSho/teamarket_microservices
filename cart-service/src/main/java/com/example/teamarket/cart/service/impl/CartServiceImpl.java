package com.example.teamarket.cart.service.impl;

import com.example.teamarket.cart.dto.request.ProductInfo;
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

/**
 * Service implementation for managing shopping cart operations.
 * This class handles interactions with product and inventory services,
 * as well as managing cart data in Redis.
 */
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
     * This method fetches the cart from Redis and maps it to a {@link CartDto}.
     *
     * @param cartUuid The UUID of the cart to retrieve.
     * @return The {@link CartDto} representing the current cart.
     * @throws ResourceNotFoundException if the cart is not found in Redis.
     */
    @Override
    public CartDto getCurrentCart(String cartUuid) {
        log.info("Fetching current cart with UUID: {}", cartUuid);
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartUuid);
        if (Objects.equals(cart, null)) {
            throw ResourceNotFoundException.of(cartUuid, Cart.class);
        }
        return cartMapper.cartToCartDto(cart);
    }

    /**
     * Adds an item to the cart. This method reserves the product in inventory
     * and then adds it to the cart if the reservation is successful.
     *
     * @param productInfo The product information including ID and weight.
     * @param uuid The UUID of the cart to which the item is added.
     */
    @Override
    public void addItemToCart(ProductInfo productInfo, String uuid) {
        log.info("Attempting to add item to cart: {} with product ID: {}", uuid, productInfo.id());
        if (inventoryServiceIntegration.reserveProduct(productInfo.id(), productInfo.weight())) {
            InfoProductDto product = productServiceIntegration.getProductById(productInfo.id());
            execute(uuid, cart -> cart.addItem(product, productInfo.weight()));
        }
    }

    /**
     * Removes an item from the cart based on its ID.
     *
     * @param id   The ID of the item to remove.
     * @param uuid The UUID of the cart from which the item will be removed.
     */
    @Override
    public void removeItemFromCart(Long id, String uuid) {
        log.info("Removing item with ID: {} from cart: {}", id, uuid);
        execute(uuid, cart -> cart.removeItemById(id));
    }

    /**
     * Clears all items from the cart associated with the given UUID.
     *
     * @param uuid The UUID of the cart to clear.
     */
    @Override
    public void clear(String uuid) {
        log.info("Clearing all items from cart: {}", uuid);
        execute(uuid, Cart::removeAllItems);
    }

    /**
     * Generates a UUID for a new cart and initializes it in Redis.
     *
     * @return A {@link StringResponse} containing the generated UUID for the new cart.
     */
    @Override
    public StringResponse generateUuid() {
        log.info("Generating new cart UUID and initializing in Redis");
        String cartUuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(cartUuid, new Cart());
        return new StringResponse(cartUuid);
    }

    /**
     * Edits an item in the cart, either by updating its quantity or removing it if the quantity is reduced to zero.
     * Reserves or returns the product to inventory as needed.
     *
     * @param cartId The UUID of the cart where the item is edited.
     * @param id The ID of the item to edit.
     * @param productInfo New product information including the updated weight.
     */
    @Override
    public void editItem(String cartId, Long id, ProductInfo productInfo) {
        log.info("Editing item with ID: {} in cart: {} with new weight: {}", id, cartId, productInfo.weight());
        if (productInfo.weight() > 0) {
            if (inventoryServiceIntegration.reserveProduct(id, productInfo.weight())) {
                execute(cartId, cart -> cart.editCartItem(id, productInfo.weight()));
            }
        }
        else {
            execute(cartId, cart -> cart.editCartItem(id, productInfo.weight()));
            inventoryServiceIntegration.returnProduct(id, productInfo.weight());
        }
    }


    private void execute(String cartUuid, Consumer<Cart> operation) {
        log.info("Executing operation on cart: {}", cartUuid);
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartUuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartUuid, Objects.requireNonNull(cart));
    }
}
