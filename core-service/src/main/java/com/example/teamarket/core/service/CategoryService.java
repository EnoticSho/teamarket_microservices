package com.example.teamarket.core.service;

import com.example.teamarket.core.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAllCategory();
    CategoryDto findById(Long id);
}
