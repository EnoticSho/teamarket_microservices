package com.example.teamarket.core.mapper;

import com.example.teamarket.core.dto.CategoryDto;
import com.example.teamarket.core.entity.Category;
import com.example.teamarket.core.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "productList", target = "productList", qualifiedByName = "mapProducts")
    CategoryDto categpryToCategoryDto(Category category);

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
