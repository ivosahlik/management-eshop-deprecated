package com.eshopweb.service.impl;

import com.eshopweb.domain.Product;
import com.eshopweb.domain.dto.ProductDto;
import com.eshopweb.repository.ProductDtoRepository;
import com.eshopweb.repository.ProductRepository;
import com.eshopweb.service.ProductService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoRepository productDtoRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .filter(Product::isActive)
                .filter(product -> !Strings.isNullOrEmpty(product.getProductNamePath()))
                .sorted(Comparator.comparing(Product::getInStockNumber).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findActiveProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .filter(Product::isActive)
                .sorted(Comparator.comparing(Product::getInStockNumber).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Product findOne(Long id) {
        Product product = productRepository.findOne(id);
        if (product.isActive()) {
            return product;
        } else {
            log.error("Product is not active!");
            throw new NullPointerException("Product is not active!");
        }
    }

    @Override
    public List<Product> findAllProductsByType(String type) {
        return productRepository.findAllProductsByType(type).stream()
                .filter(Product::isActive)
                .sorted(Comparator.comparing(Product::getInStockNumber).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllProductsByCategoryId(long typeId) {
        return new ArrayList<>(productRepository.findAllProductsByCategoryId(typeId));
    }

    @Override
    public Product findOneByProduct(String productPath) {
        return productRepository.findOneByProduct(productPath);
    }

    @Override
    public Product findOneByProductSeo(String productSeo) {
        return productRepository.findOneByProductSeo(productSeo);
    }

    @Override
    public List<ProductDto> findAllSeoByProduct(boolean active, String app) {
        return productDtoRepository.findAllSeoByProduct(active, app).stream()
                .filter(ProductDto::isActive)
                .sorted(Comparator.comparing(ProductDto::getInStockNumber).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByKeyword(String keyword, String app) {
        return productDtoRepository.findAllByKeyword("%"+ keyword.toLowerCase().trim() +"%", app).stream()
                .filter(ProductDto::isActive)
                .sorted(Comparator.comparing(ProductDto::getInStockNumber).reversed())
                .collect(Collectors.toList());
    }


}
