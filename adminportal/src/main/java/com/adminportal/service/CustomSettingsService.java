package com.adminportal.service;

import com.adminportal.domain.CustomSettings;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-15
 */
public interface CustomSettingsService {

    List<CustomSettings> findAll();

    CustomSettings findOne(Integer id);

    CustomSettings save(CustomSettings customSettings);
}
