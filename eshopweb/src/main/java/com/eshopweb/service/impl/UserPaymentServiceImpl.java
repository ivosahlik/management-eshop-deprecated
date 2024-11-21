package com.eshopweb.service.impl;

import com.eshopweb.domain.UserPayment;
import com.eshopweb.repository.UserPaymentRepository;
import com.eshopweb.service.UserPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Service
@RequiredArgsConstructor
public class UserPaymentServiceImpl implements UserPaymentService{

	private final UserPaymentRepository userPaymentRepository;

	public UserPayment findById(Long id) {
		return userPaymentRepository.findOne(id);
	}

	public void removeById(Long id) {
		userPaymentRepository.delete(id);
	}
}
