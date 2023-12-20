package com.example.teamarket.cart.controller;

import com.example.teamarket.cart.dto.CartDto;
import com.example.teamarket.cart.dto.InfoProductDto;
import com.example.teamarket.cart.model.Cart;
import com.example.teamarket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/api/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping()
    public CartDto getCart() {
        return cartService.getCurrentCart();
    }

    @DeleteMapping("/")
    public void deleteCart() {
        cartService.clear();
    }

    @DeleteMapping("/remove/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartService.removeItem(id);
    }

    @GetMapping("/add/{id}")
    public void addItemToCart(@PathVariable Long id) {
        cartService.addItemToCart(id);
    }

//    @GetMapping("/{uuid}/increment/{id}")
//    public void editCartItem(@PathVariable String uuid,
//                             @PathVariable Long id,
//                             @RequestParam int inc,
//                             Principal principal) {
//        String targetCart = getCartUuid(principal, uuid);
//        cartService.editItem(targetCart, id, inc);
//    }
//
//    private String getCartUuid(Principal principal, String uuid) {
//        return principal != null ? principal.getName() : uuid;
//    }
}
