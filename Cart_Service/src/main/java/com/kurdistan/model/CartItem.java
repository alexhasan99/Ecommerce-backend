package com.kurdistan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart; // Koppling till Cart

    @Column(name = "product_id", nullable = false)
    private String productId; // Produktens ID

    @Column(name = "quantity", nullable = false)
    private int quantity; // Antal av produkten

    @Column(name = "unit_price", nullable = false)
    private double unitPrice; // Pris per enhet
}
