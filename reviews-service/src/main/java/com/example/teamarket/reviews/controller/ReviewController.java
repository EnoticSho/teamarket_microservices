package com.example.teamarket.reviews.controller;

import com.example.teamarket.reviews.dto.request.ReviewDto;
import com.example.teamarket.reviews.dto.response.ReviewInfoDto;
import com.example.teamarket.reviews.service.impl.ReviewServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/reviews")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение всех отзывов по id продукта")
    public List<ReviewInfoDto> findAllReviewByProduct(@PathVariable("productId") Long productId) {
        return reviewService.findAllReviewByProductId(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "сохранение отзыва")
    public Long saveReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.saveReview(reviewDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "удаление отзыва по id")
    public void deleteReviewById(@PathVariable("id") Long id) {
        reviewService.deleteById(id);
    }
}
