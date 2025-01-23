package com.kurdistan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kurdistan.dto.OrderItemDTO;
import com.kurdistan.model.OrderItem;

@Mapper
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);

    OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO);
}
