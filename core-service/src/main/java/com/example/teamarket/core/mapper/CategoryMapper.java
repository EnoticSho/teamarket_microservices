package com.example.teamarket.core.mapper;

import com.example.teamarket.core.dto.response.InfoCategoryDto;
import com.example.teamarket.core.dto.response.InfoProductDto;
import com.example.teamarket.core.entity.Category;
import com.example.teamarket.core.entity.Product;
import com.example.teamarket.core.service.impl.CategoryServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CategoryServiceImpl.class)
public interface CategoryMapper {

    @Mapping(source = "productList", target = "productList", qualifiedByName = "mapProductsToInfoProductDto")
    InfoCategoryDto categoryToCategoryDto(Category category);

    @Named("mapProductsToInfoProductDto")
    default List<InfoProductDto> mapProductsToInfoProductDto(List<Product> products) {
        if (products == null) {
            return null;
        }
        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
        return products.stream()
                .map(productMapper::productToInfoProductDto)
                .collect(Collectors.toList());
    }
}
