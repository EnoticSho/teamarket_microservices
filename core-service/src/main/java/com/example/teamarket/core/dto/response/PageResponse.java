package com.example.teamarket.core.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponse<T>(List<T> content, Metadata metadata) {
    public static <T> PageResponse<T> of(Page<T> page) {
        var metadata = new Metadata(page.getNumber(), page.getSize(), page.getTotalPages());
        return new PageResponse<>(page.getContent(), metadata);
    }

    public record Metadata(int page, int size, long totalElements) {
    }
}
