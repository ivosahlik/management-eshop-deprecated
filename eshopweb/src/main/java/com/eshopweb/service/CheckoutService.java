package com.eshopweb.service;

import com.eshopweb.domain.ShoppingCart;
import org.springframework.ui.Model;

import java.math.BigDecimal;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-05-04
 */
public interface CheckoutService {

    void calculateTotalOrderWithShippingAndVat(Model model, ShoppingCart shoppingCart, String shippingAddressVat, String shippingAddressState, BigDecimal priceIncludeVat);
}
