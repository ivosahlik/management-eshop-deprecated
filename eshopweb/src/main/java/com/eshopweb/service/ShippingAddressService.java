package com.eshopweb.service;

import com.eshopweb.domain.ShippingAddress;
import com.eshopweb.domain.UserShipping;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
