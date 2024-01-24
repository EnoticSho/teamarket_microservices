package com.example.teamarket.core.service.impl;

import com.example.teamarket.core.dto.request.ProductDto;
import com.example.teamarket.core.dto.response.InfoProductDto;
import com.example.teamarket.core.dto.response.ReviewInfoDto;
import com.example.teamarket.core.entity.Category;
import com.example.teamarket.core.entity.Product;
import com.example.teamarket.core.entity.ProductImageEntity;
import com.example.teamarket.core.exception.ResourceNotFoundException;
import com.example.teamarket.core.integrations.ReviewServiceIntegration;
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

/**
 * Implementation of the ProductService interface that provides product-related functionality.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ReviewServiceIntegration reviewServiceIntegration;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    /**
     * Retrieves a list of products with optional filters.
     *
     * @param maxPrice The maximum price filter for products.
     * @param minPrice The minimum price filter for products.
     * @param title    The title filter for products.
     * @param page     The page number for pagination.
     * @param count    The number of products to retrieve per page.
     * @return A list of InfoProductDto objects representing the filtered products.
     */
    @Override
    public List<InfoProductDto> findAllProducts(Integer maxPrice, Integer minPrice, String title, int page, int count) {
        Specification<Product> specByFilter = createSpecByFilter(maxPrice, minPrice, title);
        return productRepository.findAll(specByFilter, PageRequest.of(page, count)).stream()
                .map(productMapper::productToInfoProductDto)
                .toList();
    }

    /**
     * Retrieves a product by its ID, including its reviews.
     *
     * @param id The ID of the product to retrieve.
     * @return An InfoProductDto object representing the product with reviews.
     * @throws ResourceNotFoundException if the product with the given ID is not found.
     */
    @Override
    public InfoProductDto findById(Long id) {
        List<ReviewInfoDto> productById = reviewServiceIntegration.getProductReviewsById(id);
        InfoProductDto infoProductDto = productRepository.findById(id)
                .map(productMapper::productToInfoProductDto)
                .orElseThrow(() -> ResourceNotFoundException.of(id, Product.class));
        infoProductDto.setReviewInfoDto(productById);
        return infoProductDto;
    }

    /**
     * Saves a new product.
     *
     * @param productDto The ProductDto object representing the new product.
     * @return The ID of the newly created product.
     */
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

    /**
     * Updates an existing product.
     *
     * @param id         The ID of the product to update.
     * @param productDto The ProductDto object representing the updated product.
     * @return The ID of the updated product.
     */
    public Long updateProduct(Long id, ProductDto productDto) {
        return null;
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to delete.
     */
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
