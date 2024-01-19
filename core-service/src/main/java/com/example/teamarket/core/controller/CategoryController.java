package com.example.teamarket.core.controller;

import com.example.teamarket.core.dto.response.InfoCategoryDto;
import com.example.teamarket.core.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Получение всех категорий")
    public List<InfoCategoryDto> getAllProducts() {
        return categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение категории по id")
    private InfoCategoryDto getProductById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }
}
