package com.eshopweb.repository;

import com.eshopweb.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByname(String name);
}
