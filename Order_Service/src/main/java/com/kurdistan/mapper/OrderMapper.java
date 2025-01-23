package com.kurdistan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.kurdistan.dto.OrderDTO;
import com.kurdistan.model.Order;

@Mapper(uses = {OrderItemMapper.class}) // Om Order innehåller OrderItems
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "items", target = "items") // Mappa listan av OrderItems
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "items", target = "items") // Mappa tillbaka från DTO till entitet
    Order orderDTOToOrder(OrderDTO orderDTO);
}
