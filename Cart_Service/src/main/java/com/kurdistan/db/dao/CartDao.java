package com.kurdistan.db.dao;

import com.kurdistan.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(String userId);
}
