package com.example.teamarket.inventory.repository;

import com.example.teamarket.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Product, Long> {
}
