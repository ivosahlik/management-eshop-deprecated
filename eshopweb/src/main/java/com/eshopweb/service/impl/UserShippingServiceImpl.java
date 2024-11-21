package com.eshopweb.service.impl;

import com.eshopweb.domain.UserShipping;
import com.eshopweb.repository.UserShippingRepository;
import com.eshopweb.service.UserShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RequiredArgsConstructor
@Service
public class UserShippingServiceImpl implements UserShippingService{

	private final UserShippingRepository userShippingRepository;

	public UserShipping findById(Long id) {
		return userShippingRepository.findOne(id);
	}

	public void removeById(Long id) {
		userShippingRepository.delete(id);
	}

}
