package com.example.teamarket.core.service.impl;

import com.example.teamarket.core.dto.request.ProductDto;
import com.example.teamarket.core.dto.response.InfoProductDto;
import com.example.teamarket.core.dto.response.PageResponse;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the ProductService interface that provides product-related functionality.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    /**
     * Retrieves a list of products with optional filters.
     *
     * @param maxPrice The maximum price filter for products.
     * @param minPrice The minimum price filter for products.
     * @param name    The name filter for products.
     * @param page     The page number for pagination.
     * @param count    The number of products to retrieve per page.
     * @return A list of InfoProductDto objects representing the filtered products.
     */
    @Override
    public PageResponse<InfoProductDto> findAllProducts(Integer maxPrice, Integer minPrice, String name, int page, int count) {
        Specification<Product> specByFilter = createSpecByFilter(maxPrice, minPrice, name);
        Page<InfoProductDto> map = productRepository.findAll(specByFilter, PageRequest.of(page, count))
                .map(productMapper::productToInfoProductDto);
        return PageResponse.of(map);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return An InfoProductDto object representing the product with reviews.
     * @throws ResourceNotFoundException if the product with the given ID is not found.
     */
    @Override
    @Cacheable("products")
    public InfoProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::productToInfoProductDto)
                .orElseThrow(() -> ResourceNotFoundException.of(id, Product.class));
    }

    /**
     * Saves a new product.
     *
     * @param productDto The ProductDto object representing the new product.
     * @return The ID of the newly created product.
     */
    public Long saveProduct(@Valid ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);

        product.setCategory(categoryRepository.findByName(productDto.category())
                .orElseGet(() -> Category.builder()
                        .name(productDto.category())
                        .build()));

        product.setImagesLinks(productDto.imagesLinks().stream()
                .map((link) -> ProductImageEntity.builder()
                        .product(product)
                        .imageUrl(link)
                        .build())
                .toList());

        return productRepository.save(product).getProductId();
    }

    /**
     * Updates an existing product.
     *
     * @param id         The ID of the product to update.
     * @param productDto The ProductDto object representing the updated product.
     * @return The ID of the updated product.
     */
    @Override
    @CachePut(value = "products", key = "#id")
    public Long updateProduct(Long id, ProductDto productDto) {
        return null;
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    @Override
    @CacheEvict(value = "products", key = "#id")
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private Specification<Product> createSpecByFilter(Integer maxPrice, Integer minPrice, String name) {
        Specification<Product> specification = Specification.where(null);

        if (minPrice != null) {
            specification = specification.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (name != null && !name.trim().isEmpty()) {
            specification = specification.and(ProductSpecifications.nameLike(name));
        }

        return specification;
    }
}
