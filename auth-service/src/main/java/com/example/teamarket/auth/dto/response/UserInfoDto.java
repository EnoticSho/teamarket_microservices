package com.example.teamarket.auth.dto.response;

public record UserInfoDto(
        Long id,
        String email,
        String name,
        String address) {
}
