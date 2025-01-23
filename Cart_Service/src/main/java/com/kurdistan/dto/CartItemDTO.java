package com.kurdistan.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private String productId;
    private int quantity;
    private double unitPrice;
}
