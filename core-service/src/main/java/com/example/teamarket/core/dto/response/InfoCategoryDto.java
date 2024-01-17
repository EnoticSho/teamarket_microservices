package com.example.teamarket.core.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class InfoCategoryDto {
    private String name;
    private String description;
    private List<String> productList;
}
