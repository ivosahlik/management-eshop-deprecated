package com.eshopweb.repository;

import com.eshopweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	User findByEmail(String email);

    User findBySessionId(String sessionId);
}
