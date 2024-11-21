package com.eshopweb.service;

import com.eshopweb.domain.ShoppingCart;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface ShoppingCartService {

	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

	void clearShoppingCart(ShoppingCart shoppingCart);

}
