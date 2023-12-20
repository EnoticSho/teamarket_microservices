package com.example.teamarket.core.service.impl;

import com.example.teamarket.core.dto.CategoryDto;
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
    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Override
    public List<CategoryDto> findAllCategory() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categpryToCategoryDto)
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::categpryToCategoryDto)
                .orElseThrow(() -> new ResourceNotFoundException("Категория не найдена, id: " + id));
    }
}
