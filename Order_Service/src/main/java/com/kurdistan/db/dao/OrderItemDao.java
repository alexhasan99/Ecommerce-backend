package com.kurdistan.db.dao;

import com.kurdistan.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem, Long> {
    // Alla standard CRUD-metoder hanteras automatiskt av JpaRepository
}
