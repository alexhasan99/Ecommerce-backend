package com.kurdistan.db.dao;


import com.kurdistan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {

    Product findProductById(Long id);
    List<Product> findProductsByCategory(String category);
    List<Product> findProductsByTitle(String title);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);
}
