package com.example.teamarket.core.mapper;

import com.example.teamarket.core.dto.response.InfoCategoryDto;
import com.example.teamarket.core.dto.response.InfoProductDto;
import com.example.teamarket.core.entity.Category;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-04T00:09:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public InfoCategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        List<InfoProductDto> productList = null;
        String name = null;
        String description = null;

        productList = mapProductsToInfoProductDto( category.getProductList() );
        name = category.getName();
        description = category.getDescription();

        InfoCategoryDto infoCategoryDto = new InfoCategoryDto( name, description, productList );

        return infoCategoryDto;
    }
}
