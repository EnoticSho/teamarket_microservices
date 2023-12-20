package com.example.teamarket.controller;

import com.example.teamarket.dto.InfoProductDto;
import com.example.teamarket.entity.Product;
import com.example.teamarket.mapper.ProductMapper;
import com.example.teamarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;



    @GetMapping
    public List<InfoProductDto> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    private Product getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }
}
