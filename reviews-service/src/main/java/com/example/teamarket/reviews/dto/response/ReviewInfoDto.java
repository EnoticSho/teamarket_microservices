package com.example.teamarket.reviews.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInfoDto {
    private Long id;
    private String userEmail;
    private Integer rating;
    private String comment;
    private Timestamp timestamp;
}
