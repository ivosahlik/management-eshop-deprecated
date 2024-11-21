package com.adminportal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service("authSuccessHandler")
public class AuthSuccessHandlerService implements AuthenticationSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthSuccessHandlerService.class);


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		System.out.println("uzivatel se prihlasil: " + authentication.getName());
		LOGGER.info("uzivatel se prihlasil: {} ", authentication.getName());
		request.getSession().setAttribute("username", "Ivo Vošahlík");
		response.sendRedirect(request.getContextPath());
	}
}
