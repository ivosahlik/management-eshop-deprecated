package com.adminportal.repository;

import com.adminportal.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query(value = "delete product_to_cart_item, cart_item " +
            "from product_to_cart_item " +
            "inner join cart_item on cart_item.product_id = product_to_cart_item.product_id " +
            "where product_to_cart_item.product_id = ?1", nativeQuery = true)
    void deleteForeignKey(Long id);

    @Query(value = "select count(*) from product p where p.product_name = ?1", nativeQuery = true)
    Integer isProductName(String productName);
}
