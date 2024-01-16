package com.example.teamarket.order.controller;

import com.example.teamarket.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Long saveOrder(@RequestHeader(name = "email") String email) {
        return orderService.saveOrder(email);
    }
}
