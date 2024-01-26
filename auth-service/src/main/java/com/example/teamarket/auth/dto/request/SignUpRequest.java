package com.example.teamarket.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        @Size(min = 2, max = 50, message = "Имя пользователя должно содержать от 2 до 50 символов")
        @NotBlank(message = "Имя пользователя не может быть пустыми")
        String name,

        @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
        @NotBlank(message = "Адрес электронной почты не может быть пустыми")
        @Email(message = "Email адрес должен быть в формате user@example.com")
        String email,

        @NotBlank
        @Size(max = 255, message = "Длина пароля должна быть не более 255 символов")
        String password,

        @NotBlank(message = "Адрес не может быть пустыми")
        @Size(min = 5, max = 50, message = "Адрес должно содержать от 5 до 50 символов")
        String address) {
}
