package com.example.teamarket.core.controller;

import com.example.teamarket.core.dto.response.InfoCategoryDto;
import com.example.teamarket.core.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing product categories.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Retrieves all product categories.
     *
     * @return A list of {@link InfoCategoryDto} representing all categories.
     */
    @GetMapping
    @Operation(summary = "Retrieve all categories")
    public List<InfoCategoryDto> getAllCategories() {
        return categoryService.findAllCategory();
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The {@link InfoCategoryDto} representing the category with the specified ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by ID")
    private InfoCategoryDto getCategoryById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }
}
