package com.example.teamarket.core.service.impl;

import com.example.teamarket.core.dto.InfoProductDto;
import com.example.teamarket.core.exception.ResourceNotFoundException;
import com.example.teamarket.core.mapper.ProductMapper;
import com.example.teamarket.core.repository.ProductRepository;
import com.example.teamarket.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Override
    public List<InfoProductDto> findAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::productToInfoProductDto)
                .toList();
    }

    @Override
    public InfoProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::productToInfoProductDto)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
    }
}
