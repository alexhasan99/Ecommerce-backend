package com.kurdistan.db.dao;

import com.kurdistan.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    // Hämta alla ordrar för en specifik användare
    List<Order> findByUserId(String userId);

    // Hämta alla ordrar baserat på status
    List<Order> findByStatus(String status);
}
