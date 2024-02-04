package com.example.teamarket.inventory.service.impl;

import com.example.teamarket.inventory.entity.Product;
import com.example.teamarket.inventory.exceptions.ResourceEndedInStock;
import com.example.teamarket.inventory.exceptions.ResourceNotFoundException;
import com.example.teamarket.inventory.repository.InventoryRepository;
import com.example.teamarket.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional
    public boolean reserveProduct(Long productId, int quantity) {
        Product product = checkAvailability(productId);
        if (quantity > product.getStockQuantity()) {
            System.out.println(1);
            throw ResourceEndedInStock.of(product);
        }
        System.out.println(2);
        product.setStockQuantity(product.getStockQuantity() - quantity);
        inventoryRepository.save(product);

        return true;
    }

    @Override
    @Transactional
    public void returnProduct(Long productId, int quantity) {
        Product product = checkAvailability(productId);

        product.setStockQuantity(product.getStockQuantity() + Math.abs(quantity));
        inventoryRepository.save(product);
    }

    private Product checkAvailability(Long productId) {
        return inventoryRepository.findById(productId)
                .orElseThrow(() -> ResourceNotFoundException.of(productId, Product.class));
    }
}
