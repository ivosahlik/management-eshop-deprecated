package com.eshopweb.service;

import com.eshopweb.domain.UserPayment;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface UserPaymentService {
	UserPayment findById(Long id);

	void removeById(Long id);
}
