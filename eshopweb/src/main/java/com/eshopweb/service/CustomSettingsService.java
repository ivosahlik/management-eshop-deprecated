package com.eshopweb.service;

import com.eshopweb.domain.CustomSettings;

import java.util.Optional;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-06
 */
public interface CustomSettingsService {
    Optional<CustomSettings> getOneByActiveCustomSettings();
}
