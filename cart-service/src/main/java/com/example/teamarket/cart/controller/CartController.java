package com.example.teamarket.cart.controller;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.dto.response.StringResponse;
import com.example.teamarket.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/generate_uuid")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание uuid корзины")
    public StringResponse generateUuid() {
        return cartService.generateUuid();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение корзины по uuid")
    public CartDto getCart(@RequestHeader(name = "cart_id") String cartId) {
        return cartService.getCurrentCart(cartId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Очистка корзины по uuid")
    public void deleteCart(@RequestHeader(name = "cart_id") String cartId) {
        cartService.clear(cartId);
    }

    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удаление продукта из корзины по id")
    public void deleteCartItem(@RequestHeader(name = "cart_id") String cartId,
                               @PathVariable("id") Long id) {
        cartService.removeItemFromCart(id, cartId);
    }

    @PostMapping("/add/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Добавление продукта в корзину по id")
    public void addItemToCart(@RequestHeader(name = "cart_id") String cartId,
                              @RequestParam(name = "weight") Integer weight,
                              @PathVariable("id") Long id) {
        cartService.addItemToCart(id, weight, cartId);
    }


    @GetMapping("/increment/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Изменение продукта корзины по id")
    public void editCartItem(@RequestHeader(name = "cart_id") String cartId,
                             @PathVariable Long id,
                             @RequestParam("weight") int weight) {
        cartService.editItem(cartId, id, weight);
    }
}
