package com.example.teamarket.core.service.impl;

import com.example.teamarket.core.dto.request.ProductDto;
import com.example.teamarket.core.dto.response.InfoProductDto;
import com.example.teamarket.core.entity.Category;
import com.example.teamarket.core.entity.Product;
import com.example.teamarket.core.entity.ProductImageEntity;
import com.example.teamarket.core.exception.ResourceNotFoundException;
import com.example.teamarket.core.mapper.ProductMapper;
import com.example.teamarket.core.repository.CategoryRepository;
import com.example.teamarket.core.repository.ProductRepository;
import com.example.teamarket.core.service.ProductService;
import com.example.teamarket.core.specification.ProductSpecifications;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public List<InfoProductDto> findAllProducts(Integer maxPrice, Integer minPrice, String title, int page, int count) {
        Specification<Product> specByFilter = createSpecByFilter(maxPrice, minPrice, title);
        return productRepository.findAll(specByFilter, PageRequest.of(page, count)).stream()
                .map(productMapper::productToInfoProductDto)
                .toList();
    }

    @Override
    public InfoProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::productToInfoProductDto)
                .orElseThrow(() -> ResourceNotFoundException.of(id, Product.class));
    }


    public Long saveProduct(@Valid ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);

        product.setCategory(categoryRepository.findByName(productDto.getCategory())
                .orElseGet(() -> Category.builder()
                        .name(productDto.getCategory())
                        .build()));

        product.setImagesLinks(productDto.getImagesLinks().stream()
                .map((link) -> ProductImageEntity.builder()
                        .product(product)
                        .imageUrl(link)
                        .build())
                .toList());

        return productRepository.save(product).getProductId();
    }

    public Long updateProduct(Long id, ProductDto productDto) {
        return null;
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

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
