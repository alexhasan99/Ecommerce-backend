package com.kurdistan.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private String productId; // Koppling till produkten
    private int quantity;
    private double unitPrice;
}
