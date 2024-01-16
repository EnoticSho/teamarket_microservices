package com.example.teamarket.core.service.impl;

import com.example.teamarket.core.dto.response.InfoCategoryDto;
import com.example.teamarket.core.entity.Category;
import com.example.teamarket.core.exception.ResourceNotFoundException;
import com.example.teamarket.core.mapper.CategoryMapper;
import com.example.teamarket.core.repository.CategoryRepository;
import com.example.teamarket.core.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<InfoCategoryDto> findAllCategory() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDto)
                .toList();
    }

    @Override
    public InfoCategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::categoryToCategoryDto)
                .orElseThrow(() -> ResourceNotFoundException.of(id, Category.class));
    }
}
