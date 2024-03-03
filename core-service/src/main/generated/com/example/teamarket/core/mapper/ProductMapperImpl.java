package com.example.teamarket.core.mapper;

import com.example.teamarket.core.dto.request.ProductDto;
import com.example.teamarket.core.dto.response.InfoProductDto;
import com.example.teamarket.core.entity.Product;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-23T04:08:53+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( productDto.name() );
        product.description( productDto.description() );
        product.price( productDto.price() );
        product.stockQuantity( productDto.stockQuantity() );
        product.effect( productDto.effect() );

        return product.build();
    }

    @Override
    public InfoProductDto productToInfoProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        List<String> imagesLinks = null;
        String category = null;
        Long productId = null;
        String name = null;
        String description = null;
        BigDecimal price = null;
        Integer stockQuantity = null;
        String effect = null;
        Timestamp created = null;
        Timestamp updated = null;

        imagesLinks = mapImageLinksToString( product.getImagesLinks() );
        category = mapCategory( product.getCategory() );
        productId = product.getProductId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        stockQuantity = product.getStockQuantity();
        effect = product.getEffect();
        created = product.getCreated();
        updated = product.getUpdated();

        InfoProductDto infoProductDto = new InfoProductDto( productId, name, description, price, stockQuantity, effect, imagesLinks, category, created, updated );

        return infoProductDto;
    }
}
