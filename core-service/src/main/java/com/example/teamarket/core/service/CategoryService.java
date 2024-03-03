package com.example.teamarket.core.service;

import com.example.teamarket.core.dto.response.InfoCategoryDto;

import java.util.List;

public interface CategoryService {
    List<InfoCategoryDto> findAllCategory();
    InfoCategoryDto findByName(String title);
}
