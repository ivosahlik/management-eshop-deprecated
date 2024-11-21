package com.eshopweb.repository;

import com.eshopweb.domain.User;
import com.eshopweb.domain.security.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken(String token);

	PasswordResetToken findByUser(User user);

	Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

	@Modifying
	@Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
	void deleteAllExpiredSince(Date now);

}
