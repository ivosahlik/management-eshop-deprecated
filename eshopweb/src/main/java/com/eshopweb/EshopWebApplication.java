package com.eshopweb;

import com.eshopweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@EnableCaching
@SpringBootApplication
public class EshopWebApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(EshopWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user1 = new User();
//		user1.setFirstName("Ivo");
//		user1.setLastName("Vošahlík");
//		user1.setUsername("user");
//		user1.setPassword(SecurityUtility.passwordEncoder().encode("user"));
//		user1.setEmail("ivosahlik@seznam.cz");
//		Set<UserRole> userRoles = new HashSet<>();
//		Role role1= new Role();
//		role1.setRoleId(1);
//		role1.setName("ROLE_USER");
//		userRoles.add(new UserRole(user1, role1));
//
//		userService.createUser(user1, userRoles);
	}
}
