package com.example.teamarket.cart.mapper;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.dto.response.CartItemDto;
import com.example.teamarket.cart.model.Cart;
import com.example.teamarket.cart.model.CartItem;
import com.example.teamarket.cart.service.impl.CartServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mappings({
            @Mapping(target = "itemsMap", source = "itemsMap", qualifiedByName = "mapToDtoList"),
            @Mapping(target = "totalCost", source = "totalCost")
    })
    CartDto cartToCartDto(Cart cart);

    @Named("mapToDtoList")
    default List<CartItemDto> mapToDtoList(Map<Long, CartItem> map) {
        return map.values().stream()
                .map(this::cartItemToCartItemDto)
                .collect(Collectors.toList());
    }

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "weight", source = "weight"),
            @Mapping(target = "costByHundredGrams", source = "costByHundredGrams"),
            @Mapping(target = "sum", source = "sum")
    })
    CartItemDto cartItemToCartItemDto(CartItem cartItem);
}
