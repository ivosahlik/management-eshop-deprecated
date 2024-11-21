package com.adminportal.service;

import java.util.Set;

import com.adminportal.domain.User;
import com.adminportal.domain.security.UserRole;


/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface UserService {
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
}
