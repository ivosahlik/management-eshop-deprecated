package com.eshopweb.service.impl;

import com.eshopweb.domain.User;
import com.eshopweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService{

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if(null == user) {
			throw new UsernameNotFoundException("Username not found");
		}

		return user;
	}

}
