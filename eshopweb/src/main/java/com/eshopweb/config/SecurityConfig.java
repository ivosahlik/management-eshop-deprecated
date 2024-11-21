package com.eshopweb.config;

import com.eshopweb.service.impl.UserSecurityService;
import com.eshopweb.utility.SecurityUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Intellij Idea
 * Created by ivosahlik on 30/12/2018
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserSecurityService userSecurityService;
	private final AuthSuccessHandler authSuccessHandler;
	private final EshopwebLogoutHandler eshopwebLogoutHandler;
	private final EshopwebLogoutSuccessHandler eshopwebLogoutSuccessHandler;

	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}

	private static final String[] PUBLIC_MATCHERS = {
			"/styles/**",
			"/js/**",
			"/images/**",
			"/plugins/**",
			"/",
			"/newUser",
			"/newUserAccount",
			"/forgetPassword",
			"/login",
			"/fonts/**",
			"/hours",
			"/faq",
			"/searchByCategory",
			"/searchProduct",
			"/products/**",
			"/api/**",
			"/file/**",
			"/getAQoute/**",
			"/category/**",
			"/categories/**",
			"/favicon/**",
			"/termsAndConditions",
			"/gdpr",
			"/aboutUs",
			"/google28dde2f3fd6b0d0a.html",
			"/support",
			"/actuator/**",
			"/health-check",
			"/shoppingCart/**",
			"/checkout/**",
			"/ajax/checkout/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
			.anonymous().principal("guest").authorities("GUEST_ROLE")
			.and()
			.authorizeRequests()
		/*	antMatchers("/**").*/
			.antMatchers(PUBLIC_MATCHERS)
			.permitAll().anyRequest().authenticated();

		http
			.csrf()
				.disable()
				.cors()
				.disable()
			.formLogin().loginPage("/login").failureUrl("/login?error")
				.defaultSuccessUrl("/")
				.successHandler(authSuccessHandler)
				.loginPage("/login").permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
				.addLogoutHandler(eshopwebLogoutHandler)
				.logoutSuccessHandler(eshopwebLogoutSuccessHandler)
			.and()
				.rememberMe();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}

}
