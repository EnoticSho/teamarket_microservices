package com.example.teamarket.core.controller;

import com.example.teamarket.core.dto.request.ProductDto;
import com.example.teamarket.core.dto.response.InfoProductDto;
import com.example.teamarket.core.dto.response.PageResponse;
import com.example.teamarket.core.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing products.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Retrieves all products with optional filtering criteria.
     *
     * @param minPrice The minimum price filter (optional).
     * @param maxPrice The maximum price filter (optional).
     * @param name    The title filter (optional).
     * @param page     The page number for pagination (default is 0).
     * @param count    The number of items per page (default is 10).
     * @return A list of {@link InfoProductDto} representing the products.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve all products with filters")
    public PageResponse<InfoProductDto> getAllProducts(
            @RequestParam(required = false, name = "min_price") Integer minPrice,
            @RequestParam(required = false, name = "max_price") Integer maxPrice,
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(defaultValue = "0", name = "p") int page,
            @RequestParam(defaultValue = "10", name = "count") int count
    ) {
        page = page > 0 ? page : 0;
        count = count > 0 ? count : 10;

        return productService.findAllProducts(maxPrice, minPrice, name, page, count);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The {@link InfoProductDto} representing the product with the specified ID.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve a product by ID")
    public InfoProductDto getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    /**
     * Creates a new product.
     *
     * @param productDto The {@link ProductDto} representing the product to be created.
     * @return The ID of the newly created product.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save a product")
    public Long createProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize(value = "hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update a product")
    public Long updateProduct(@PathVariable Long id,
                              @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a product")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }
}
