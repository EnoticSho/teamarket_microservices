package com.example.teamarket.core.mapper;

import com.example.teamarket.core.dto.response.InfoCategoryDto;
import com.example.teamarket.core.entity.Category;
import com.example.teamarket.core.entity.Product;
import com.example.teamarket.core.service.impl.CategoryServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CategoryServiceImpl.class)
public interface CategoryMapper {

    @Mapping(source = "productList", target = "productList", qualifiedByName = "mapProducts")
    InfoCategoryDto categoryToCategoryDto(Category category);

    @Named("mapProducts")
    default List<String> mapProducts(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }
}
