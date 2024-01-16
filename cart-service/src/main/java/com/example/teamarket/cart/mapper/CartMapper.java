package com.example.teamarket.cart.mapper;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDto cartToCartDto(Cart cart);
}
