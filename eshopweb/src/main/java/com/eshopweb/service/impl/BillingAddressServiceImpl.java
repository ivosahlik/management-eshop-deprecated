package com.eshopweb.service.impl;

import com.eshopweb.domain.BillingAddress;
import com.eshopweb.domain.UserBilling;
import com.eshopweb.service.BillingAddressService;
import org.springframework.stereotype.Service;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Service
public class BillingAddressServiceImpl implements BillingAddressService{

	public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) {
		billingAddress.setBillingAddressName(userBilling.getUserBillingName());
		billingAddress.setBillingAddressStreet1(userBilling.getUserBillingStreet1());
		billingAddress.setBillingAddressStreet2(userBilling.getUserBillingStreet2());
		billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
		billingAddress.setBillingAddressState(userBilling.getUserBillingState());
		billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());
		billingAddress.setBillingAddressZipcode(userBilling.getUserBillingZipcode());

		return billingAddress;
	}

}
