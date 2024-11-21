package com.eshopweb.service.impl;

import com.eshopweb.domain.ShippingAddress;
import com.eshopweb.domain.UserShipping;
import com.eshopweb.service.ShippingAddressService;
import org.springframework.stereotype.Service;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
	public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {
		shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
		shippingAddress.setShippingAddressStreet1(userShipping.getUserShippingStreet1());
		shippingAddress.setShippingAddressStreet2(userShipping.getUserShippingStreet2());
		shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
		shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
		shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
		shippingAddress.setShippingAddressZipcode(userShipping.getUserShippingZipcode());
		shippingAddress.setShippingAddressPhone(userShipping.getUserShippingPhone());
		shippingAddress.setShippingAddressVat(userShipping.getUserShippingVat());

		return shippingAddress;
	}
}
