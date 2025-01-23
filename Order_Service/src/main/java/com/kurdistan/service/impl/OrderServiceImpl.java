package com.kurdistan.service.impl;

import com.kurdistan.db.dao.OrderDao;
import com.kurdistan.db.dao.OrderItemDao;
import com.kurdistan.dto.OrderDTO;
import com.kurdistan.mapper.OrderMapper;
import com.kurdistan.model.Order;
import com.kurdistan.service.interfaces.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;

    public OrderServiceImpl(OrderDao orderDao, OrderItemDao orderItemDao) {
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderDao.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return OrderMapper.INSTANCE.orderToOrderDTO(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderDTO);
        Order savedOrder = orderDao.save(order);

        return OrderMapper.INSTANCE.orderToOrderDTO(savedOrder);
    }

    public List<OrderDTO> getOrdersByUserId(String userId) {
        List<Order> orders = orderDao.findByUserId(userId);
        return orders.stream()
                .map(OrderMapper.INSTANCE::orderToOrderDTO)
                .toList();
    }
}
