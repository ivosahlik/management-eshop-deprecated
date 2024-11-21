package com.eshopweb.service;

import com.eshopweb.domain.BillingAddress;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.Payment;
import com.eshopweb.domain.ShippingAddress;
import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.domain.User;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart,
					  ShippingAddress shippingAddress,
					  BillingAddress billingAddress,
					  Payment payment,
					  String shippingMethod,
					  User user);

	Order findOne(Long id);
}
