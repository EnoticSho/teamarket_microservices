package com.example.teamarket.cart.mapper;

import com.example.teamarket.cart.dto.response.CartDto;
import com.example.teamarket.cart.dto.response.CartItemDto;
import com.example.teamarket.cart.model.Cart;
import com.example.teamarket.cart.model.CartItem;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-23T04:08:44+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CartDto cartToCartDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        List<CartItemDto> itemsMap = null;
        BigDecimal totalCost = null;

        itemsMap = mapToDtoList( cart.getItemsMap() );
        totalCost = cart.getTotalCost();

        CartDto cartDto = new CartDto( itemsMap, totalCost );

        return cartDto;
    }

    @Override
    public CartItemDto cartItemToCartItemDto(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Integer weight = null;
        BigDecimal costByHundredGrams = null;
        BigDecimal sum = null;
        String imagesLink = null;

        id = cartItem.getId();
        name = cartItem.getName();
        weight = cartItem.getWeight();
        costByHundredGrams = cartItem.getCostByHundredGrams();
        sum = cartItem.getSum();
        imagesLink = cartItem.getImagesLink();

        CartItemDto cartItemDto = new CartItemDto( id, name, weight, costByHundredGrams, sum, imagesLink );

        return cartItemDto;
    }
}
