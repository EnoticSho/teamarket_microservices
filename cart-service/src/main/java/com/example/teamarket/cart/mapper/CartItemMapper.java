package com.example.teamarket.cart.mapper;

import com.example.teamarket.cart.dto.CartItemDto;
import com.example.teamarket.cart.model.CartItem;
import org.mapstruct.Mapper;

@Mapper
public interface CartItemMapper {
    CartItemDto cartItemToCartItemDto(CartItem cartItem);
    CartItem cartItemDtoToCartItem(CartItemDto cartItemDto);
}
