package com.eshopweb.repository;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	List<CartItem> findByOrder(Order order);
}
