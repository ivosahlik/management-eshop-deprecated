package com.adminportal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Intellij Idea
 * Created by ivosahlik on 30/12/2018
 */
@Service
public class AdminportalLogoutHandler implements LogoutHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminportalAuthFailureHandler.class);

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        LOGGER.info("AdminportalLogoutHandler");
    }

}
