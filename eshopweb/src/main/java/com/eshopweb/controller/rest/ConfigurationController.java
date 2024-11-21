package com.eshopweb.controller.rest;

import com.eshopweb.service.impl.ConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Intellij Idea
 * Created by ivosahlik on 13/06/2018
 */
@Slf4j
@RestController
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @CrossOrigin
    @GetMapping("/favicon")
    public ResponseEntity<InputStreamResource> getFavicon() throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(configurationService.getFavicon());
    }

}
