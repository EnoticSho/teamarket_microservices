package com.example.teamarket.core.repository;

import com.example.teamarket.core.entity.Product;
import com.example.teamarket.core.integrations.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductRepositoryTest extends IntegrationTestBase {

    private final ProductRepository productRepository;

    @Test
    public void findById() {
        Optional<Product> byId = productRepository.findById(1L);
        System.out.println(byId);
    }
}
