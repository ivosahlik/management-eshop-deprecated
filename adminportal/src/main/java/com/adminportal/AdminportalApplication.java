package com.adminportal;

import com.adminportal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Slf4j
@SpringBootApplication
public class AdminportalApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AdminportalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		User user1 = new User();
//		user1.setUsername("admin");
//		user1.setFirstName("Ivo");
//		user1.setLastName("Vošahlík");
//		user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
//		user1.setEmail("admin@gmail.com");
//		Set<UserRole> userRoles = new HashSet<>();
//		Role role1= new Role();
//		role1.setRoleId(2);
//		role1.setName("ROLE_ADMIN");
//		userRoles.add(new UserRole(user1, role1));
//
//		userService.createUser(user1, userRoles);

	}

//	public static void main(String[] args) {
//		SecurityUtility.passwordEncoder().encode("admin");
//	}
}
