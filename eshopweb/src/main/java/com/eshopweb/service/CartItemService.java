package com.eshopweb.service;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.Product;
import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.domain.User;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface CartItemService {

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	CartItem updateCartItem(CartItem cartItem);

	CartItem addProductToCartItem(Product product, User user, int qty);

	CartItem findById(Long id);

	void removeCartItem(CartItem cartItem);

	CartItem save(CartItem cartItem);

	List<CartItem> findByOrder(Order order);
}
