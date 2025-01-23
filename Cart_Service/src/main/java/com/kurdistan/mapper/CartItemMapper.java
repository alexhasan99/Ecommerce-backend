package com.kurdistan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kurdistan.dto.CartItemDTO;
import com.kurdistan.model.CartItem;

@Mapper
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

    CartItem cartItemDTOToCartItem(CartItemDTO cartItemDTO);
}
