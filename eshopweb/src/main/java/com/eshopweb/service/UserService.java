package com.eshopweb.service;

import com.eshopweb.domain.User;
import com.eshopweb.domain.UserBilling;
import com.eshopweb.domain.UserPayment;
import com.eshopweb.domain.UserShipping;
import com.eshopweb.domain.security.PasswordResetToken;
import com.eshopweb.domain.security.UserRole;

import java.util.Set;


/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);

	void createPasswordResetTokenForUser(final User user, final String token);

	User findByUsername(String username);

	User findByEmail (String email);

	User findById(Long id);

	User createUser(User user, Set<UserRole> userRoles) throws Exception;

	User save(User user);

	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

	void updateUserShipping(UserShipping userShipping, User user);

	void setUserDefaultPayment(Long userPaymentId, User user);

	void setUserDefaultShipping(Long userShippingId, User user);

    User findBySessionId(String sessionId);
}
