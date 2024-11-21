package com.eshopweb.repository;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.ProductToCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Transactional
public interface ProductToCartItemRepository extends JpaRepository<ProductToCartItem, Long> {
	void deleteByCartItem(CartItem cartItem);
}
