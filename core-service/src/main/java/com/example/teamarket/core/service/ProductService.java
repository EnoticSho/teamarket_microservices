package com.example.teamarket.core.service;

import com.example.teamarket.core.dto.request.ProductDto;
import com.example.teamarket.core.dto.response.InfoProductDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<InfoProductDto> findAllProducts(Integer maxPrice, Integer minPrice, String title, int page, int count);
    InfoProductDto findById(Long id);
    Long saveProduct(ProductDto productDto);
    void deleteById(Long id);
}
