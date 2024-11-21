package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.User;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
