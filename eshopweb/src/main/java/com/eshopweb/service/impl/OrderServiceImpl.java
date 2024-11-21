package com.eshopweb.service.impl;

import com.eshopweb.domain.BillingAddress;
import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.Payment;
import com.eshopweb.domain.Product;
import com.eshopweb.domain.ShippingAddress;
import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.domain.User;
import com.eshopweb.repository.OrderRepository;
import com.eshopweb.service.CartItemService;
import com.eshopweb.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private final CartItemService cartItemService;

	public synchronized Order createOrder(ShoppingCart shoppingCart,
										  ShippingAddress shippingAddress,
										  BillingAddress billingAddress,
										  Payment payment,
										  String shippingMethod,
										  User user) {
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		for(CartItem cartItem : cartItemList) {
			Product product = cartItem.getProduct();
			cartItem.setOrder(order);
			product.setInStockNumber(product.getInStockNumber() - cartItem.getQty());
		}

		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());

		order.setOrderTotal(shoppingCart.getGrandTotal());
		order.setOrderGrandTotalVat(shoppingCart.getGrandTotalVat());
		order.setOrderShipping(shoppingCart.getShipping());
		order.setOrderGrandWidthTotal(shoppingCart.getGrandWidthTotal());
		order.setOrderSumTotalOrderWithShippingAndIncludeVat(shoppingCart.getSumTotalOrderWithShippingAndIncludeVat());

		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);

		return order;
	}

	public Order findOne(Long id) {
		return orderRepository.findOne(id);
	}

}
