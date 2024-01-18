package com.example.teamarket.reviews.entities.listener;

import com.example.teamarket.reviews.entities.Review;
import jakarta.persistence.PrePersist;

import java.sql.Timestamp;
import java.time.Instant;

public class ReviewListener {

    @PrePersist
    protected void OnCreate(Review review) {
        review.setTimestamp(Timestamp.from(Instant.now()));
    }
}
