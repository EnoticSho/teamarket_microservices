package com.example.teamarket.reviews.service;

import com.example.teamarket.reviews.dto.request.ReviewDto;
import com.example.teamarket.reviews.dto.response.ReviewInfoDto;

import java.util.List;

public interface ReviewService {
    List<ReviewInfoDto> findAllReviewByProductId(Long productID);
    Long saveReview(ReviewDto reviewDto);
    void deleteById(Long id);
}
