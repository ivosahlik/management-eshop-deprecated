package com.eshopweb.service;

import com.eshopweb.domain.BillingAddress;
import com.eshopweb.domain.UserBilling;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
