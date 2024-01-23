package com.example.teamarket.reviews.mapper;

import com.example.teamarket.reviews.dto.request.ReviewDto;
import com.example.teamarket.reviews.dto.response.ReviewInfoDto;
import com.example.teamarket.reviews.entities.Review;
import com.example.teamarket.reviews.service.impl.ReviewServiceImpl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ReviewServiceImpl.class})
public interface ReviewMapper {

    ReviewInfoDto entityToInfoDto(Review review);

    Review dtoToEntity(ReviewDto reviewDto);
}
