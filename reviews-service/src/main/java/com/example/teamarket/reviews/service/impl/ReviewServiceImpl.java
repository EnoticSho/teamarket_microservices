package com.example.teamarket.reviews.service.impl;

import com.example.teamarket.reviews.dto.request.ReviewDto;
import com.example.teamarket.reviews.dto.response.ReviewInfoDto;
import com.example.teamarket.reviews.entities.Review;
import com.example.teamarket.reviews.mapper.ReviewMapper;
import com.example.teamarket.reviews.repository.ReviewRepository;
import com.example.teamarket.reviews.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    /**
     * Retrieves all reviews for a given product by its ID.
     *
     * @param productID The ID of the product to fetch reviews for.
     * @return A list of review information DTOs.
     */
    public List<ReviewInfoDto> findAllReviewByProductId(Long productID) {
        return reviewRepository.findByProductId(productID).stream()
                .map(reviewMapper::entityToInfoDto)
                .toList();
    }

    /**
     * Saves a new review.
     *
     * @param reviewDto The review DTO to be saved.
     * @return The ID of the saved review.
     */
    public Long saveReview(ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToEntity(reviewDto);

        return reviewRepository.save(review).getId();
    }

    /**
     * Deletes a review by its ID.
     *
     * @param id The ID of the review to be deleted.
     */
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
