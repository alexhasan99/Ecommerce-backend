package com.kurdistan.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId; // Koppling till användaren (från User Service)

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>(); // Lista av OrderItems

    @Column(name = "total_price")
    private double totalPrice; // Totalbelopp för hela ordern

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "status")
    private String status; // T.ex. "PENDING", "COMPLETED", "CANCELLED"
}
