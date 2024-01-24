package com.example.teamarket.cart.controller;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.dto.response.StringResponse;
import com.example.teamarket.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing shopping carts and cart-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/cart")
public class CartController {

    private final CartService cartService;

    /**
     * Generates a UUID for a new shopping cart.
     *
     * @return A StringResponse containing the generated UUID.
     */
    @GetMapping("/generate_uuid")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Generate cart UUID", description = "Generates a UUID for a new shopping cart.")
    public StringResponse generateUuid() {
        return cartService.generateUuid();
    }

    /**
     * Retrieves the contents of a shopping cart based on its UUID.
     *
     * @param cartId The UUID of the cart to retrieve.
     * @return A CartDto representing the cart's contents.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get cart by UUID", description = "Retrieves the contents of a shopping cart based on its UUID.")
    public CartDto getCart(@RequestHeader(name = "cart_id") String cartId) {
        return cartService.getCurrentCart(cartId);
    }

    /**
     * Clears the contents of a shopping cart based on its UUID.
     *
     * @param cartId The UUID of the cart to clear.
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Clear cart by UUID", description = "Clears the contents of a shopping cart based on its UUID.")
    public void deleteCart(@RequestHeader(name = "cart_id") String cartId) {
        cartService.clear(cartId);
    }

    /**
     * Removes a specific item from a shopping cart based on its ID and the cart's UUID.
     *
     * @param cartId The UUID of the cart.
     * @param id     The ID of the item to remove.
     */
    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove item from cart", description = "Removes a specific item from a shopping cart based on its ID and the cart's UUID.")
    public void deleteCartItem(@RequestHeader(name = "cart_id") String cartId,
                               @PathVariable("id") Long id) {
        cartService.removeItemFromCart(id, cartId);
    }

    /**
     * Adds a specific item to a shopping cart based on its ID and the cart's UUID.
     *
     * @param cartId The UUID of the cart.
     * @param weight The weight of the item to add.
     * @param id     The ID of the item to add.
     */
    @PostMapping("/add/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Add item to cart", description = "Adds a specific item to a shopping cart based on its ID and the cart's UUID.")
    public void addItemToCart(@RequestHeader(name = "cart_id") String cartId,
                              @RequestParam(name = "weight") Integer weight,
                              @PathVariable("id") Long id) {
        cartService.addItemToCart(id, weight, cartId);
    }

    /**
     * Edits the quantity of a specific item in a shopping cart based on its ID and the cart's UUID.
     *
     * @param cartId The UUID of the cart.
     * @param id     The ID of the item to edit.
     * @param weight The new weight (quantity) of the item.
     */
    @GetMapping("/increment/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit item in cart", description = "Edits the quantity of a specific item in a shopping cart based on its ID and the cart's UUID.")
    public void editCartItem(@RequestHeader(name = "cart_id") String cartId,
                             @PathVariable Long id,
                             @RequestParam("weight") int weight) {
        cartService.editItem(cartId, id, weight);
    }
}
