package com.kurdistan.service.impl;

import com.kurdistan.db.dao.ProductDao;
import com.kurdistan.dto.ProductDTO;
import com.kurdistan.mapper.ProductMapper;
import com.kurdistan.model.Product;
import com.kurdistan.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        product = productDao.save(product);
        return productMapper.productToProductDTO(product);
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productDao.findById(id).map(productMapper::productToProductDTO);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productDao.findAll().stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        product.setId(id);
        product = productDao.save(product);
        return productMapper.productToProductDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }
}
