package com.adminportal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.time.LocalDateTime;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-23
 */
@Component
@ManagedResource(objectName = "bean:name=dataSlowDataAccessor")
@Slf4j
public class DataSlowDataAdminAccessor implements ServletContextAware {

    private ServletContext servletContext;

    @PostConstruct
    private void init() {
        getData();
    }

    /**
     * Is executed periodically every 120000 millis.
     */
    @ManagedOperation
    @Scheduled(fixedRate = 120_000, initialDelay = 120_000)
    private void refreshDataHolders() {
        getData();
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private void getData() {
        log.info("loaded new data from db - {}", LocalDateTime.now());

    }
}
