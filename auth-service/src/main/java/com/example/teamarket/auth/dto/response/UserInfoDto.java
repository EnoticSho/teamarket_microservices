package com.example.teamarket.auth.dto.response;

import com.example.teamarket.auth.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private Long id;
    private String email;
    private String name;
    private String address;
}
