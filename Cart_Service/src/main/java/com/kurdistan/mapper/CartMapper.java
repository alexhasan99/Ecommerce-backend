package com.kurdistan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kurdistan.dto.CartDTO;
import com.kurdistan.model.Cart;

@Mapper(uses = {CartItemMapper.class})
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDTO cartToCartDTO(Cart cart);

    Cart cartDTOToCart(CartDTO cartDTO);
}
