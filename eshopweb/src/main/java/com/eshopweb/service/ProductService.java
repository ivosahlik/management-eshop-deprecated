package com.eshopweb.service;

import com.eshopweb.domain.Product;
import com.eshopweb.domain.dto.ProductDto;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    List<Product> findActiveProducts();

    Product findOne(Long id);

    List<Product> findAllProductsByType(String type);

    List<Product> findAllProductsByCategoryId(long typeId);

    Product findOneByProduct(String productPath);

    Product findOneByProductSeo(String productSeo);

    List<ProductDto> findAllSeoByProduct(boolean active, String app);

    List<ProductDto> findAllByKeyword(String keyword, String app);
}
