package com.adminportal.service.impl;

import com.adminportal.domain.Product;
import com.adminportal.repository.ProductRepository;
import com.adminportal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void removeOne(Long id) {
        productRepository.deleteForeignKey(id);
        productRepository.delete(id);
    }

    @Override
    public boolean isProductName(String productName) {
        return productRepository.isProductName(productName) > 0;
    }
}
