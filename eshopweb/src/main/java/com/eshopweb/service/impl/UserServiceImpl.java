package com.eshopweb.service.impl;

import com.eshopweb.domain.ShoppingCart;
import com.eshopweb.domain.User;
import com.eshopweb.domain.UserBilling;
import com.eshopweb.domain.UserPayment;
import com.eshopweb.domain.UserShipping;
import com.eshopweb.domain.security.PasswordResetToken;
import com.eshopweb.domain.security.UserRole;
import com.eshopweb.repository.PasswordResetTokenRepository;
import com.eshopweb.repository.RoleRepository;
import com.eshopweb.repository.UserPaymentRepository;
import com.eshopweb.repository.UserRepository;
import com.eshopweb.repository.UserShippingRepository;
import com.eshopweb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserPaymentRepository userPaymentRepository;
	private final UserShippingRepository userShippingRepository;
	private final PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findBySessionId(String sessionId) {
		return userRepository.findBySessionId(sessionId);
	}

	@Override
	public User findById(Long id){
		return userRepository.findOne(id);
	}

	@Override
	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles){
		User localUser = userRepository.findByUsername(user.getUsername());

		if(Objects.nonNull(localUser)) {
			log.info("user {} already exists. Nothing will be done.", user.getUsername());
		} else {
			userRoles.forEach(ur -> roleRepository.save(ur.getRole()));
			user.getUserRoles().addAll(userRoles);
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			user.setShoppingCart(shoppingCart);
			user.setUserShippingList(new ArrayList<>());
			user.setUserPaymentList(new ArrayList<>());

			localUser = userRepository.save(user);
		}

		return localUser;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);
	}

	@Override
	public void updateUserShipping(UserShipping userShipping, User user){
		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippingList().add(userShipping);
		save(user);
	}

	@Override
	public void setUserDefaultPayment(Long userPaymentId, User user) {
		userPaymentRepository.findAll().forEach(userPayment -> {
			if(userPayment.getId().equals(userPaymentId)) {
				userPayment.setDefaultPayment(true);
				userPaymentRepository.save(userPayment);
			} else {
				userPayment.setDefaultPayment(false);
				userPaymentRepository.save(userPayment);
			}
		});
	}

	@Override
	public void setUserDefaultShipping(Long userShippingId, User user) {
		userShippingRepository.findAll().forEach(userShipping -> {
			if(userShipping.getId().equals(userShippingId)) {
				userShipping.setUserShippingDefault(true);
				userShippingRepository.save(userShipping);
			} else {
				userShipping.setUserShippingDefault(false);
				userShippingRepository.save(userShipping);
			}
		});
	}
}
