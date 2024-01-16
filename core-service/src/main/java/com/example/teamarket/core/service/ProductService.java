package com.example.teamarket.core.service;

import com.example.teamarket.core.dto.response.InfoProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<InfoProductDto> findAllProducts(Integer maxPrice, Integer minPrice, String title, Integer page);
    InfoProductDto findById(Long id);
}
