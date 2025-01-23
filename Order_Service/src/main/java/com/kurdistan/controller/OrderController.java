package com.kurdistan.controller;

import com.kurdistan.dto.OrderDTO;
import com.kurdistan.service.interfaces.OrderService;
import com.kurdistan.security.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    private final JwtUtils jwtUtils;

    public OrderController(OrderService orderService, JwtUtils jwtUtils) {
        this.orderService = orderService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(
            @RequestBody OrderDTO orderDTO,
            @RequestHeader("Authorization") String authHeader) {
        // Hämta userId från JWT-token
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtUtils.getUserIdFromToken(token);

        // Sätt userId i orderDTO
        orderDTO.setUserId(userId);

        // Skapa ordern
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // Hämta order med ID
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String authHeader) {
        // Hämta userId från JWT-token
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtUtils.getUserIdFromToken(token);

        // Kontrollera att ordern tillhör användaren
        OrderDTO order = orderService.getOrderById(orderId);
        if (!order.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(order);
    }

    // Hämta alla ordrar för en användare
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getUserOrders(
            @RequestHeader("Authorization") String authHeader) {
        // Hämta userId från JWT-token
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtUtils.getUserIdFromToken(token);

        // Hämta ordrar för användaren
        List<OrderDTO> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

}
