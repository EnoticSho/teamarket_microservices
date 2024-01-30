package com.example.teamarket.core.mapper;

import com.example.teamarket.core.dto.request.ProductDto;
import com.example.teamarket.core.dto.response.InfoProductDto;
import com.example.teamarket.core.entity.Category;
import com.example.teamarket.core.entity.Product;
import com.example.teamarket.core.entity.ProductImageEntity;
import com.example.teamarket.core.service.impl.ProductServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ProductServiceImpl.class})
public interface ProductMapper {

    @Mapping(target = "productId", ignore = true)
    @Mapping(source = "imagesLinks", target = "imagesLinks", ignore = true)
    @Mapping(source = "category", target = "category", ignore = true)
    Product toProduct(ProductDto productDto);

    @Mapping(target = "reviewInfoDto", ignore = true)
    @Mapping(source = "imagesLinks", target = "imagesLinks", qualifiedByName = "mapImageLinksToString")
    @Mapping(source = "category", target = "category", qualifiedByName = "mapCategory")
    InfoProductDto productToInfoProductDto(Product product);

//    @Mapping(target = "productId", ignore = true)
//    @Mapping(source = "category", target = "category", qualifiedByName = "mapStringToCategory")
//    Product merge(@MappingTarget Product product, ProductDto productDto);

    @Named("mapImageLinksToString")
    default List<String> mapImageLinksToString(List<ProductImageEntity> imageEntities) {
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
