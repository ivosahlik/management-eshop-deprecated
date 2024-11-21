package com.adminportal.service;

import com.adminportal.domain.Product;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Product findOne(Long id);

    void removeOne(Long id);

    boolean isProductName(String productName);

}
