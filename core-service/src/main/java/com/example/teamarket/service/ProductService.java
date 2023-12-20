package com.example.teamarket.service;

import com.example.teamarket.dto.InfoProductDto;
import com.example.teamarket.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<InfoProductDto> findAllProducts();
    InfoProductDto findById(Long id);
}
