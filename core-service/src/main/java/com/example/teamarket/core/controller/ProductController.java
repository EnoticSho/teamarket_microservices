package com.example.teamarket.core.controller;

import com.example.teamarket.core.dto.response.InfoProductDto;
import com.example.teamarket.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InfoProductDto> getAllProducts(
            @RequestParam(required = false, name = "min_price") Integer minPrice,
            @RequestParam(required = false, name = "max_price") Integer maxPrice,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(defaultValue = "0", name = "p") Integer page
    ) {
        page = page > 0 ? page : 0;

        return productService.findAllProducts(maxPrice, minPrice, title, page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private InfoProductDto getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }
}
