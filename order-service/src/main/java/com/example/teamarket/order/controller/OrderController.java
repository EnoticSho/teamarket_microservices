package com.example.teamarket.order.controller;

import com.example.teamarket.order.dto.request.payment.CardInfo;
import com.example.teamarket.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling order-related endpoints.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * Create a new order based on the provided cart ID and user email.
     *
     * @param cartId The cart ID associated with the order.
     * @param email  The user's email.
     * @return The ID of the created order.
     */
    @PostMapping
    @Operation(summary = "Create an order")
    public Long saveOrder(@RequestHeader(name = "cart_id") String cartId,
                          @RequestHeader(name = "email") String email) {
        return orderService.saveOrder(cartId, email);
    }

    /**
     * Send a payment request for the specified order ID using the provided card information.
     *
     * @param id       The ID of the order for payment.
     * @param cardInfo The card information used for payment.
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Send a payment request")
    public void processPayment(@PathVariable("id") Long id,
                               @RequestBody CardInfo cardInfo) {
        orderService.sendPaymentRequest(id, cardInfo);
    }
}
