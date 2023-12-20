package com.example.teamarket.mapper;

import com.example.teamarket.dto.InfoProductDto;
import com.example.teamarket.entity.Category;
import com.example.teamarket.entity.Product;
import com.example.teamarket.entity.ProductImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "imagesLinks", target = "imagesLinks", qualifiedByName = "mapImageLinks")
    @Mapping(source = "category", target = "category", qualifiedByName = "mapCategory")
    InfoProductDto productToInfoProductDto(Product product);

    @Named("mapImageLinks")
    default List<String> mapImageLinks(List<ProductImageEntity> imageEntities) {
        if (imageEntities == null) {
            return null;
        }
        return imageEntities.stream()
                .map(ProductImageEntity::getImageUrl)
                .collect(Collectors.toList());
    }

    @Named("mapCategory")
    default String mapCategory(Category category) {
        if (category == null) {
            return null;
        }
        return category.getName();
    }
}
