package com.eshopweb.repository;

import com.eshopweb.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select i from Product i where i.typePath = ?1")
    List<Product> findAllProductsByType(String type);

    @Query("select i from Product i where i.categoryId = ?1")
    List<Product> findAllProductsByCategoryId(long typeId);

    @Query(value = "select * from product t where t.product_name_path = ?1 && t.active = true", nativeQuery = true)
    Product findOneByProduct(String productPath);

    @Query("select i from Product i where i.productNamePath = ?1")
    Product findOneByProductSeo(String productSeo);

}
