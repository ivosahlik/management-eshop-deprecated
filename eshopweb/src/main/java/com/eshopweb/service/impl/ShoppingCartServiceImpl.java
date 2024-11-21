package com.eshopweb.service.impl;

import com.eshopweb.domain.CartItem;
import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.repository.ShoppingCartRepository;
import com.eshopweb.service.CartItemService;
import com.eshopweb.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	private final CartItemService cartItemService;
	private final ShoppingCartRepository shoppingCartRepository;

	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {

		if (Objects.isNull(shoppingCart)) {
			return null;
		}

		BigDecimal cartTotal = new BigDecimal(0);
		BigDecimal cartWeightTotal = new BigDecimal(0);

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		for (CartItem cartItem : cartItemList) {
			if(cartItem.getProduct().getInStockNumber() > 0) {
				cartItemService.updateCartItem(cartItem);
				cartTotal = cartTotal.add(cartItem.getSubtotal());
				cartWeightTotal = cartWeightTotal.add(cartItem.getSubweight());
			}
		}

		shoppingCart.setGrandTotal(cartTotal);
		shoppingCart.setGrandWidthTotal(cartWeightTotal);

		shoppingCartRepository.save(shoppingCart);

		return shoppingCart;
	}

	public void clearShoppingCart(ShoppingCart shoppingCart) {
		cartItemService.findByShoppingCart(shoppingCart)
				.forEach(cartItem -> {
					cartItem.setShoppingCart(null);
					cartItemService.save(cartItem);
		});

		shoppingCart.setGrandTotal(new BigDecimal(0));

		shoppingCartRepository.save(shoppingCart);
	}

}
