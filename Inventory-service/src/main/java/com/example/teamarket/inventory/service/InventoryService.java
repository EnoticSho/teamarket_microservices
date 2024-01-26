package com.example.teamarket.inventory.service;

public interface InventoryService {

    boolean reserveProduct(Long productId, int quantity);

    void returnProduct(Long productId, int quantity);
}
