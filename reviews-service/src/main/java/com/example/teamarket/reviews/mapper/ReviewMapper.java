package com.example.teamarket.reviews.mapper;

import com.example.teamarket.reviews.dto.request.ReviewDto;
import com.example.teamarket.reviews.dto.response.ReviewInfoDto;
import com.example.teamarket.reviews.entities.Review;
import com.example.teamarket.reviews.service.ReviewService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReviewService.class)
public interface ReviewMapper {

    Review dtoToEntity(ReviewDto reviewDto);

    ReviewInfoDto entityToInfoDto(Review review);
}
