package com.example.teamarket.order.controller;

import com.example.teamarket.order.dto.request.payment.CardInfo;
import com.example.teamarket.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Создание заказа")
    public Long saveOrder(@RequestHeader(name = "cart_id") String cartId,
                          @RequestHeader(name = "email") String email) {
        return orderService.saveOrder(cartId, email);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Отправка запроса на оплату")
    public void processPayment(@PathVariable("id") Long id,
                               @RequestBody CardInfo cardInfo) {
        orderService.sendPaymentRequest(id, cardInfo);
    }
}
