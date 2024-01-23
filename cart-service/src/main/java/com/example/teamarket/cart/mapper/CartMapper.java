package com.example.teamarket.cart.mapper;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.model.Cart;
import com.example.teamarket.cart.service.impl.CartServiceImpl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDto modelToDto(Cart cart);
}
