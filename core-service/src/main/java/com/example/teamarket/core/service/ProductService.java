package com.example.teamarket.core.service;

import com.example.teamarket.core.dto.InfoProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<InfoProductDto> findAllProducts();
    InfoProductDto findById(Long id);
}
