package com.eshopweb.service;

import com.eshopweb.domain.UserShipping;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface UserShippingService {
	UserShipping findById(Long id);

	void removeById(Long id);
}
