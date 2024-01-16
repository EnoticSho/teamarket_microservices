package com.example.teamarket.cart.controller;

import com.example.teamarket.cart.dto.CartDto;
import com.example.teamarket.cart.dto.StringResponse;
import com.example.teamarket.cart.model.Cart;
import com.example.teamarket.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/{uuid}")
    public Cart getCart(@PathVariable("uuid") String uuid) {
        return cartService.getCurrentCart(uuid);
    }

    @DeleteMapping("/{uuid}")
    public void deleteCart(@PathVariable("uuid") String uuid) {
        cartService.clear(uuid);
    }

    @DeleteMapping("/{uuid}/remove/{id}")
    public void deleteCartItem(@PathVariable("uuid") String uuid,
                               @PathVariable("id") Long id) {
        cartService.removeItemFromCart(id, uuid);
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addItemToCart(@PathVariable("uuid") String uuid,
                              @PathVariable("id") Long id) {
        cartService.addItemToCart(id, uuid);
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
