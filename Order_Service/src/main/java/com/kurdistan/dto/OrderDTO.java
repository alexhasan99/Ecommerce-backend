package com.kurdistan.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String userId;
    private List<OrderItemDTO> items; // Lista med OrderItemDTO
    private Date createdDate;
    private double totalPrice;
    private String status;
}
