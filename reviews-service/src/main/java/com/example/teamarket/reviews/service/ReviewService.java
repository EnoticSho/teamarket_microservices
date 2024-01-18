package com.example.teamarket.reviews.service;

import com.example.teamarket.reviews.dto.request.ReviewDto;
import com.example.teamarket.reviews.dto.response.ReviewInfoDto;
import com.example.teamarket.reviews.entities.Review;
import com.example.teamarket.reviews.mapper.ReviewMapper;
import com.example.teamarket.reviews.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public List<ReviewInfoDto> findAllReviewByProductId(Long productID) {
        return reviewRepository.findByProductId(productID).stream()
                .map(reviewMapper::entityToInfoDto)
                .toList();
    }

    public Long saveReview(ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToEntity(reviewDto);

        return reviewRepository.save(review).getId();
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
