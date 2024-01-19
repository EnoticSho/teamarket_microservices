package com.example.teamarket.cart.mapper;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.model.Cart;
import com.example.teamarket.cart.service.impl.CartServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = CartServiceImpl.class)
public interface CartMapper {
    CartDto cartToCartDto(Cart cart);
}
