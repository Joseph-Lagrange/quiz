package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductPO> findAll() {
        return productRepository.findAll();
    }

    public ProductPO save(Product product) {
        ProductPO productPO = new ProductPO();
        productPO.setName(product.getName());
        productPO.setPrice(product.getPrice());
        productPO.setUnit(product.getUnit());
        productPO.setUrl(product.getUrl());
        productPO = productRepository.save(productPO);
        return productPO;
    }
}
