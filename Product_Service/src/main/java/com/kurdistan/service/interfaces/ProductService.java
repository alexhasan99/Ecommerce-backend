package com.kurdistan.service.interfaces;

import com.kurdistan.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    Optional<ProductDTO> getProductById(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
