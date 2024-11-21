package com.eshopweb.service;

import com.eshopweb.domain.Payment;
import com.eshopweb.domain.UserPayment;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
