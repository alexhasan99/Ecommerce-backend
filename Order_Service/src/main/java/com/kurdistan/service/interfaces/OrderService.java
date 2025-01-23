package com.kurdistan.service.interfaces;

import com.kurdistan.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO getOrderById(Long orderId);

    OrderDTO createOrder(OrderDTO orderDTO);

    public List<OrderDTO> getOrdersByUserId(String userId);
}
