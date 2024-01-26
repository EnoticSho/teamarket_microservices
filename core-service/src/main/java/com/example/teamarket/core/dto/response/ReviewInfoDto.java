package com.example.teamarket.core.dto.response;

import java.sql.Timestamp;

public record ReviewInfoDto(
        Long id,
        String userEmail,
        Integer rating,
        String comment,
        Timestamp timestamp) {
}
