package com.adminportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.adminportal.service.impl.UserSecurityService;
import com.adminportal.utility.SecurityUtility;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSecurityService userSecurityService;

	@Autowired
	private AuthSuccessHandlerService authSuccessHandlerService;

	@Autowired
	private AdminportalLogoutHandler adminportalLogoutHandler;

	@Autowired
	private AdminportalLogoutSuccessHandler adminportalLogoutSuccessHandler;

	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}

	private static final String[] PUBLIC_MATCHERS = {
			"/styles/**",
			"/js/**",
			"/images/**",
			"/newUser",
			"/forgetPassword",
			"/login",
			"/fonts/**",
			"/health-check"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers("/*").hasRole("ADMIN")
					.antMatchers(PUBLIC_MATCHERS)
					.permitAll()
					.anyRequest()
					.authenticated();

		http
				.csrf().disable().cors().disable()
				.formLogin().failureUrl("/login?error")
					.defaultSuccessUrl("/")
					.successHandler(authSuccessHandlerService)
					//.failureHandler(adminportalAuthFailureHandler)
					.loginPage("/login").permitAll()
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.permitAll()
					.addLogoutHandler(adminportalLogoutHandler)
					.logoutSuccessHandler(adminportalLogoutSuccessHandler);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}

}
