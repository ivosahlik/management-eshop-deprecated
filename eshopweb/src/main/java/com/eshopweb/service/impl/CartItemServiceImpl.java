package com.eshopweb.service.impl;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.Order;
import com.eshopweb.domain.Product;
import com.eshopweb.domain.ProductToCartItem;
import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.domain.User;
import com.eshopweb.repository.CartItemRepository;
import com.eshopweb.repository.ProductToCartItemRepository;
import com.eshopweb.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService{

	private final CartItemRepository cartItemRepository;
	private final ProductToCartItemRepository productToCartItemRepository;

	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart).stream()
				.filter(cartItem -> cartItem.getQty() != 0)
				.collect(Collectors.toList());
	}

	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal subtotalBigDecimal = BigDecimal.valueOf(cartItem.getProduct().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
		BigDecimal weightBigDecimal = BigDecimal.valueOf(cartItem.getProduct().getShippingWeight()).multiply(new BigDecimal(cartItem.getQty()));

		subtotalBigDecimal = subtotalBigDecimal.setScale(2, RoundingMode.HALF_UP);
		weightBigDecimal = weightBigDecimal.setScale(0, RoundingMode.HALF_UP);
		cartItem.setSubtotal(subtotalBigDecimal);
		cartItem.setSubweight(weightBigDecimal);

		cartItemRepository.save(cartItem);

		return cartItem;
	}

	public CartItem addProductToCartItem(Product product, User user, int qty) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());

		for (CartItem cartItem : cartItemList) {
			if (product.getId().equals(cartItem.getProduct().getId())) {
				cartItem.setQty(cartItem.getQty()+qty);
				cartItem.setSubtotal(BigDecimal.valueOf(product.getOurPrice()).multiply(new BigDecimal(qty)));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}

		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setProduct(product);

		cartItem.setQty(qty);
		cartItem.setSubtotal(BigDecimal.valueOf(product.getOurPrice()).multiply(new BigDecimal(qty)));
		cartItem = cartItemRepository.save(cartItem);

		ProductToCartItem productToCartItem = new ProductToCartItem();
		productToCartItem.setProduct(product);
		productToCartItem.setCartItem(cartItem);
		productToCartItemRepository.save(productToCartItem);

		return cartItem;
	}

	public CartItem findById(Long id) {
		return cartItemRepository.findOne(id);
	}

	public void removeCartItem(CartItem cartItem) {
		productToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}

	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	public List<CartItem> findByOrder(Order order) {
		return cartItemRepository.findByOrder(order);
	}
}
