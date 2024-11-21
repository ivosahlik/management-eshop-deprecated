package com.adminportal.config;

import com.adminportal.core.featuretoggling.FeatureToggler;
import com.adminportal.interceptor.CoreInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Intellij Idea
 * Created by ivosahlik on 24/12/2018
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public FeatureToggler featureToggler() {
        return new FeatureToggler();
    }

    @Bean
    public CoreInterceptor coreInterceptor() {
        return new CoreInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(coreInterceptor())
        .addPathPatterns("/**")
        .addPathPatterns("/login");
    }
}
