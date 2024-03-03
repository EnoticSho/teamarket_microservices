package com.example.teamarket.core.service.impl;

import com.example.teamarket.core.dto.response.InfoCategoryDto;
import com.example.teamarket.core.entity.Category;
import com.example.teamarket.core.exception.ResourceNotFoundException;
import com.example.teamarket.core.mapper.CategoryMapper;
import com.example.teamarket.core.repository.CategoryRepository;
import com.example.teamarket.core.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the CategoryService interface that provides category-related functionality.
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    /**
     * Retrieves a list of all categories.
     *
     * @return A list of InfoCategoryDto objects representing the categories.
     */
    @Override
    public List<InfoCategoryDto> findAllCategory() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDto)
                .toList();
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return An InfoCategoryDto object representing the category.
     * @throws ResourceNotFoundException if the category with the given ID is not found.
     */
    @Override
    @Transactional
    public InfoCategoryDto findByName(String title) {
        return categoryRepository.findByName(title)
                .map(categoryMapper::categoryToCategoryDto)
                .orElseThrow(() -> ResourceNotFoundException.of(title, Category.class));
    }
}
