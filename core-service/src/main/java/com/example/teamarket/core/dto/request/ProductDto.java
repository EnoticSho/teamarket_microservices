package com.example.teamarket.core.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
        @Size(min = 1, max = 50, message = "Название чая должно содержать от 1 до 50 символов")
        @NotBlank(message = "Название чая не может быть пустым")
        String name,

        @NotBlank(message = "Описание не может быть пустым")
        String description,

        @DecimalMin(value = "0.00", message = "Цена не может быть меньше 0")
        BigDecimal price,

        @Min(value = 0, message = "Количество на складе не может быть отрицательным")
        Integer stockQuantity,

        @NotBlank(message = "Эффект не может быть пустым")
        String effect,

        @NotEmpty(message = "Должна быть предоставлена хотя бы одна ссылка на изображение")
        List<String> imagesLinks,

        @NotBlank(message = "Категория не может быть пустой")
        String category) {
}
