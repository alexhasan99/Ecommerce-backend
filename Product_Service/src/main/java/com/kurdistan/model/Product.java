package com.kurdistan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "descr")
    private String descr;

    @Column(name = "price")
    private double price;

    @Column(name = "image")
    private String image;

    @Column(name = "category")
    private String category;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "owner_id")
    private Long owner_id;
}
