package com.adminportal.interceptor;

import com.adminportal.core.featuretoggling.FeatureToggler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Intellij Idea
 * Created by ivosahlik on 24/12/2018
 */
public class CoreInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreInterceptor.class);

    @Autowired
    private FeatureToggler featureToggler;

//    private String featureTestEnabled = System.getProperty("feature.test.enabled");

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        long startTime = System.currentTimeMillis();
        LOGGER.info("\n-------- LogInterception.preHandle --- ");
        LOGGER.info("Request URL: {}", httpServletRequest.getRequestURL());
        LOGGER.info("Start Time: {}", System.currentTimeMillis());
        httpServletRequest.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        LOGGER.info("\n-------- LogInterception.postHandle --- ");
        LOGGER.info("Request URL: {}", httpServletRequest.getRequestURL());
        LOGGER.info("After handling the request");

        if (Objects.nonNull(modelAndView)) {
            boolean isRedirectView = modelAndView.getView() instanceof RedirectView;
            boolean viewNameStartsWithRedirect = Objects.nonNull(modelAndView.getViewName()) && modelAndView.getViewName().startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX);
            if (modelAndView.hasView() && !isRedirectView && !viewNameStartsWithRedirect) {
                addCommonModelData(modelAndView, httpServletRequest, httpServletResponse);
                if (!featureToggler.isFeatureTestEnabled()) {
                    LOGGER.info("-------- featureToggler.isFeatureTestEnabled is true, test is hidden  --- ");
                }
            }

        }

    }

    private void addCommonModelData(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        modelAndView.getModelMap().addAttribute("testEnabled", featureToggler.isFeatureTestEnabled());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
