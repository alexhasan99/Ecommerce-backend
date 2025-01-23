package com.kurdistan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Koppling till Order

    @Column(name = "product_id", nullable = false)
    private String productId; // Koppling till produkten (från Product Service)

    @Column(name = "quantity")
    private int quantity; // Antal av produkten

    @Column(name = "unit_price")
    private double unitPrice; // Pris per enhet för produkten
}
