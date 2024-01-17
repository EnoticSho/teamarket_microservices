package com.example.teamarket.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoUserDto {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String address;
}
