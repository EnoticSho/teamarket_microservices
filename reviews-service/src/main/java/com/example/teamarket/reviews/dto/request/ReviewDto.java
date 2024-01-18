package com.example.teamarket.reviews.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long productId;
    private String userEmail;
    private Integer rating;
    private String comment;
}
