package com.eshopweb.service.impl;

import com.eshopweb.domain.Payment;
import com.eshopweb.domain.UserPayment;
import com.eshopweb.service.PaymentService;
import org.springframework.stereotype.Service;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Service
public class PaymentServiceImpl implements PaymentService{

	public Payment setByUserPayment(UserPayment userPayment, Payment payment) {
		payment.setType(userPayment.getType());
		payment.setHolderName(userPayment.getHolderName());
		payment.setCardNumber(userPayment.getCardNumber());
		payment.setExpiryMonth(userPayment.getExpiryMonth());
		payment.setExpiryYear(userPayment.getExpiryYear());
		payment.setCvc(userPayment.getCvc());

		return payment;
	}

}
