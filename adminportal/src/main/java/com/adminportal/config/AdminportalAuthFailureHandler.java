package com.adminportal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Intellij Idea
 * Created by ivosahlik on 30/12/2018
 */
@Service
public class AdminportalAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminportalAuthFailureHandler.class);


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        LOGGER.info("AdminportalAuthFailureHandler: {}", exception.getMessage() );

        super.onAuthenticationFailure(request, response, exception);

    }
}
