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
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Implements the {@link CartService} interface to provide operations for managing shopping carts.
 * It integrates with product and inventory services for product data and stock management,
 * and uses Redis to store and retrieve cart data.
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
        Cart cart = getCartFromRedis(cartUuid);
        return cartMapper.cartToCartDto(cart);
    }

    /**
     * Adds a product to a cart. Reserves the product in the inventory service before adding.
     *
     * @param productInfo contains the product ID and weight to be added.
     * @param uuid        the UUID of the cart to which the product will be added.
     */
    @Override
    @Transactional
    public void addItemToCart(ProductInfo productInfo, String uuid) {
        log.info("Attempting to add item to cart: {} with product ID: {}", uuid, productInfo.id());
        InfoProductDto product = productServiceIntegration.getProductById(productInfo.id());
        execute(uuid, cart -> {
            if (inventoryServiceIntegration.reserveProduct(productInfo.id(), productInfo.weight())) {
                cart.addItem(product, productInfo.weight());
            }
        });
    }

    /**
     * Removes an item from the cart based on product ID and returns the product to inventory.
     *
     * @param id   the ID of the product to remove from the cart.
     * @param uuid the UUID of the cart from which the item will be removed.
     */
    @Override
    @Transactional
    public void removeItemFromCart(Long id, String uuid) {
        log.info("Removing item with ID: {} from cart: {}", id, uuid);
        execute(uuid, cart -> {
            Integer weight = cart.removeItemById(id);
            inventoryServiceIntegration.returnProduct(id, weight);
        });
    }

    /**
     * Clears all items from a cart and returns all products to inventory.
     *
     * @param uuid the UUID of the cart to be cleared.
     */
    @Override
    @Transactional
    public void clear(String uuid) {
        log.info("Clearing all items from cart: {}", uuid);
        execute(uuid, cart -> {
            Map<Long, Integer> longIntegerMap = cart.removeAllItems();
            longIntegerMap.forEach(inventoryServiceIntegration::returnProduct);
        });
    }

    /**
     * Generates a new UUID for a cart and initializes an empty cart in Redis.
     *
     * @return a response containing the generated UUID.
     */
    @Override
    public StringResponse generateUuid() {
        log.info("Generating new cart UUID");
        String cartUuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(cartUuid, new Cart());
        return new StringResponse(cartUuid);
    }

    /**
     * Edits the weight of a product in a cart. If the weight is positive, it attempts to reserve the product
     * in inventory. If the weight adjustment is negative, it returns the product to inventory.
     *
     * @param cartId      the UUID of the cart where the item is edited.
     * @param id          the ID of the product to edit.
     * @param productInfo contains the new product information including the updated weight.
     */
    @Override
    @Transactional
    public void editItem(String cartId, Long id, ProductInfo productInfo) {
        log.info("Editing item with ID: {} in cart: {} with new weight: {}", id, cartId, productInfo.weight());
        execute(cartId, cart -> {
            cart.editCartItem(id, productInfo.weight());
            if (productInfo.weight() < 0) {
                inventoryServiceIntegration.returnProduct(id, productInfo.weight());
            }
            else {
                inventoryServiceIntegration.reserveProduct(id, productInfo.weight());
            }
        });
    }

    @Override
    public void mergeCartWithUser(String cartUuid, String email) {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(email))) {
            redisTemplate.opsForValue().set(email, new Cart());
        }
        Cart cartFromRedis = getCartFromRedis(cartUuid);
        if (!cartFromRedis.getItemsMap().isEmpty()) {
            Cart userCart = (Cart) redisTemplate.opsForValue().get(email);
            Cart merge = userCart.merge(cartFromRedis);
            redisTemplate.opsForValue().set(email, merge);
            redisTemplate.delete(cartUuid);
        }
    }

    /**
     * Executes a given operation on the cart identified by UUID. Initializes a new cart in Redis if not present.
     *
     * @param cartUuid  the UUID of the cart on which the operation is to be executed.
     * @param operation the operation to be performed on the cart.
     */
    private void execute(String cartUuid, Consumer<Cart> operation) {
        log.info("Executing operation on cart: {}", cartUuid);
        Cart cart = getCartFromRedis(cartUuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartUuid, cart);
    }

    private Cart getCartFromRedis(String cartUuid) {
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartUuid);
        if (cart == null) {
            log.error("Cart not found in Redis for UUID: {}", cartUuid);
            throw ResourceNotFoundException.of(cartUuid, Cart.class);
        }
        return cart;
    }
}
