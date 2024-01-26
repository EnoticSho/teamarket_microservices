package com.example.teamarket.order.dto.response;

public record InfoUserDto(
        Long id,
        String email,
        String password,
        String name,
        String address) {
}
