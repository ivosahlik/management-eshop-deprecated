package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.security.Role;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
