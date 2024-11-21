package com.adminportal.service.impl;

import com.adminportal.domain.CustomSettings;
import com.adminportal.repository.CustomSettingsRepository;
import com.adminportal.service.CustomSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-15
 */
@Service
public class CustomSettingsServiceImpl implements CustomSettingsService {

    @Autowired
    private CustomSettingsRepository customSettingsRepository;

    @Override
    public List<CustomSettings> findAll() {
        return StreamSupport.stream(customSettingsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public CustomSettings findOne(Integer id) {
        return customSettingsRepository.findOne(id);
    }

    @Override
    public CustomSettings save(CustomSettings customSettings) {
        if (Objects.isNull(customSettings)) {
            return null;
        }
        return customSettingsRepository.save(customSettings);
    }
}
