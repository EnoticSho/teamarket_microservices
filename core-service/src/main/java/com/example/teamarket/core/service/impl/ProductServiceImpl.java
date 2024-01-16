package com.example.teamarket.core.service.impl;

import com.example.teamarket.core.dto.request.ProductDto;
import com.example.teamarket.core.dto.response.InfoProductDto;
import com.example.teamarket.core.entity.Product;
import com.example.teamarket.core.exception.ResourceNotFoundException;
import com.example.teamarket.core.mapper.ProductMapper;
import com.example.teamarket.core.repository.ProductRepository;
import com.example.teamarket.core.service.ProductService;
import com.example.teamarket.core.specification.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<InfoProductDto> findAllProducts(Integer maxPrice, Integer minPrice, String title, Integer page) {
        Specification<Product> specByFilter = createSpecByFilter(maxPrice, minPrice, title);
        return productRepository.findAll(specByFilter, PageRequest.of(page, 5)).stream()
                .map(productMapper::productToInfoProductDto)
                .toList();
    }

    @Override
    public InfoProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::productToInfoProductDto)
                .orElseThrow(() -> ResourceNotFoundException.of(id, Product.class));
    }

//    @Transactional
//    public Product saveProduct(ProductDto productDto) {
//        // Сохранение информации о продукте
//        Product savedProduct = productRepository.save(product);
//
//        // Сохранение изображений продукта
//        for (ProductImageEntity image : images) {
//            image.setProduct_id(savedProduct.getProductId());
//            productImageRepository.save(image);
//        }
//
//        return savedProduct;
//    }

    private Specification<Product> createSpecByFilter(Integer maxPrice, Integer minPrice, String title) {
        Specification<Product> specification = Specification.where(null);

        if (minPrice != null) {
            specification = specification.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (title != null && !title.trim().isEmpty()) {
            specification = specification.and(ProductSpecifications.titleLike(title));
        }

        return specification;
    }
}
