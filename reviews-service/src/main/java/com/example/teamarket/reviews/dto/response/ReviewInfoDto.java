package com.example.teamarket.reviews.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ReviewInfoDto {
    private Long id;
    private String userEmail;
    private Integer rating;
    private String comment;
    private Timestamp timestamp;
}
