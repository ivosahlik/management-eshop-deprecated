package com.eshopweb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Intellij Idea
 * Created by ivosahlik on 30/12/2018
 */
@Slf4j
@Service("authSuccessHandler")
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		log.info("uzivatel se prihlasil: {} ", authentication.getName());
		request.getSession().setAttribute("username", "Ivo Vošahlík");
		response.sendRedirect(request.getContextPath());
	}
}
