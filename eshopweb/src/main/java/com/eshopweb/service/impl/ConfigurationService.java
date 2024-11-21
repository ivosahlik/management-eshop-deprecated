package com.eshopweb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Intellij Idea
 * Created by ivosahlik on 13/06/2018
 */
@Slf4j
@Service
public class ConfigurationService {

    private static final String IMAGE_FAVICON = "/static/images/favicon32.png";

    public InputStreamResource getFavicon() throws IOException {
        ClassPathResource getFavicon = new ClassPathResource(IMAGE_FAVICON);
        return new InputStreamResource(getFavicon.getInputStream());
    }

}
