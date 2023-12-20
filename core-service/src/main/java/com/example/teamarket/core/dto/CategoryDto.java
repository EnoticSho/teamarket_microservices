package com.example.teamarket.core.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CategoryDto {

    private String name;
    private String description;
    private List<String> productList;
}
