package com.example.teamarket.cart.controller;

import com.example.teamarket.cart.dto.request.ProductInfo;
import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> generateUuid() {
        return ResponseEntity
                .ok(cartService.generateUuid());
    }

    @PostMapping("/items")
    @Operation(summary = "Add item to cart", description = "Adds a specific item to a shopping cart based on its ID and the cart's UUID.")
    public ResponseEntity<?> addItemToCart(@RequestHeader(name = "cart_id") String cartId,
                                           @RequestBody ProductInfo productInfo) {
        cartService.addItemToCart(productInfo, cartId);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping
    @Operation(summary = "Get cart by UUID", description = "Retrieves the contents of a shopping cart based on its UUID.")
    public ResponseEntity<CartDto> getCart(@RequestHeader(name = "cart_id") String cartId) {
        return ResponseEntity
                .ok(cartService.getCurrentCart(cartId));
    }

    @DeleteMapping("/items")
    @Operation(summary = "Clear cart by UUID", description = "Clears the contents of a shopping cart based on its UUID.")
    public ResponseEntity<?> deleteCart(@RequestHeader(name = "cart_id") String cartId) {
        cartService.clear(cartId);
        return ResponseEntity
                .noContent()
                .build();
    }


    @DeleteMapping("/items/{id}")
    @Operation(summary = "Remove item from cart", description = "Removes a specific item from a shopping cart based on its ID and the cart's UUID.")
    public ResponseEntity<?> deleteCartItem(@RequestHeader(name = "cart_id") String cartId,
                                            @PathVariable("id") Long id) {
        cartService.removeItemFromCart(id, cartId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/items/{id}")
    @Operation(summary = "Edit item in cart", description = "Edits the quantity of a specific item in a shopping cart based on its ID and the cart's UUID.")
    public ResponseEntity<?> editCartItem(@RequestHeader(name = "cart_id") String cartId,
                                          @PathVariable Long id,
                                          @RequestBody ProductInfo productInfo) {
        cartService.editItem(cartId, id, productInfo);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/mergeCart")
    public ResponseEntity<?> associateCartWithUser(@RequestHeader("cart_id") String cartUuid,
                                                   @RequestHeader("email") String email) {
        cartService.mergeCartWithUser(cartUuid, email);
        return ResponseEntity
                .ok()
                .build();
    }
}
