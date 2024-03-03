package com.example.teamarket.inventory.controller;

import com.example.teamarket.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/reserve/{productId}/{quantity}")
    public Boolean reserveProduct(@PathVariable("productId") Long productId, @PathVariable("quantity") int quantity) {
        return inventoryService.reserveProduct(productId, quantity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/return/{productId}/{quantity}")
    public void returnProduct(@PathVariable("productId") Long productId, @PathVariable("quantity") int quantity) {
        inventoryService.returnProduct(productId, quantity);
    }
}
