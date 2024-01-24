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

/**
 * Controller class for managing product reviews.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/reviews")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    /**
     * Retrieves all reviews for a given product by its ID.
     *
     * @param productId The ID of the product to fetch reviews for.
     * @return A list of review information DTOs.
     */
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve all reviews by product ID")
    public List<ReviewInfoDto> findAllReviewByProduct(@PathVariable("productId") Long productId) {
        return reviewService.findAllReviewByProductId(productId);
    }

    /**
     * Saves a new review.
     *
     * @param reviewDto The review DTO to be saved.
     * @return The ID of the saved review.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save a review")
    public Long saveReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.saveReview(reviewDto);
    }

    /**
     * Deletes a review by its ID.
     *
     * @param id The ID of the review to be deleted.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a review by ID")
    public void deleteReviewById(@PathVariable("id") Long id) {
        reviewService.deleteById(id);
    }
}
