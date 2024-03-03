package com.example.teamarket.reviews.mapper;

import com.example.teamarket.reviews.dto.request.ReviewDto;
import com.example.teamarket.reviews.dto.response.ReviewInfoDto;
import com.example.teamarket.reviews.entities.Review;
import java.sql.Timestamp;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-23T04:10:18+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewInfoDto entityToInfoDto(Review review) {
        if ( review == null ) {
            return null;
        }

        Long id = null;
        String userEmail = null;
        Integer rating = null;
        String comment = null;
        Timestamp timestamp = null;

        id = review.getId();
        userEmail = review.getUserEmail();
        rating = review.getRating();
        comment = review.getComment();
        timestamp = review.getTimestamp();

        ReviewInfoDto reviewInfoDto = new ReviewInfoDto( id, userEmail, rating, comment, timestamp );

        return reviewInfoDto;
    }

    @Override
    public Review dtoToEntity(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        Review.ReviewBuilder review = Review.builder();

        review.productId( reviewDto.productId() );
        review.userEmail( reviewDto.userEmail() );
        review.rating( reviewDto.rating() );
        review.comment( reviewDto.comment() );

        return review.build();
    }
}
