package com.kurdistan.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String title;
    private String descr;
    private double price;
    private String category;
    private String image;
    private Long owner_id;
    private int quantity;
}
