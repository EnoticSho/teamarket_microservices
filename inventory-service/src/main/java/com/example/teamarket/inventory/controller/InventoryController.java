package com.example.teamarket.inventory.controller;

import com.example.teamarket.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/reserve/{productId}/{quantity}")
    public Boolean reserveProduct(@PathVariable Long productId, @PathVariable int quantity) {
        return inventoryService.reserveProduct(productId, quantity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/return/{productId}/{quantity}")
    public void returnProduct(@PathVariable Long productId, @PathVariable int quantity) {
        inventoryService.returnProduct(productId, quantity);
    }
}
