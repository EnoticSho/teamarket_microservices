package com.example.teamarket.cart.controller;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.dto.response.StringResponse;
import com.example.teamarket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/api/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return cartService.generateUuid();
    }

    @GetMapping
    public CartDto getCart(@RequestHeader(name = "cart_id") String cartId) {
        return cartService.getCurrentCart(cartId);
    }

    @DeleteMapping
    public void deleteCart(@RequestHeader(name = "cart_id") String cartId) {
        cartService.clear(cartId);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteCartItem(@RequestHeader(name = "cart_id") String cartId,
                               @PathVariable("id") Long id) {
        cartService.removeItemFromCart(id, cartId);
    }

    @GetMapping("/add/{id}")
    public void addItemToCart(@RequestHeader(name = "cart_id") String cartId,
                              @RequestParam(name = "weight") Integer weight,
                              @PathVariable("id") Long id) {
        cartService.addItemToCart(id, weight, cartId);
    }


    @GetMapping("/increment/{id}")
    public void editCartItem(@RequestHeader(name = "cart_id") String cartId,
                             @PathVariable Long id,
                             @RequestParam("weight") int weight) {
        cartService.editItem(cartId, id, weight);
    }
}
