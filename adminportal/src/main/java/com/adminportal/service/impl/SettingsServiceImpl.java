package com.adminportal.service.impl;

import com.adminportal.domain.Settings;
import com.adminportal.repository.SettingsRepository;
import com.adminportal.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 27/12/2018
 */
@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public Settings save(Settings settings) {
        return settingsRepository.save(settings);
    }

    @Override
    public Settings findOne(Long id) {
        return settingsRepository.findOne(id);
    }

    @Override
    public List<Settings> findAll() {
        return StreamSupport.stream(settingsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(long id) {
        settingsRepository.delete(id);
    }

    @Override
    public List<Settings> findOneActive() {
        return StreamSupport.stream(settingsRepository.findAll().spliterator(), false)
                .filter(Settings::isActive)
                .limit(1)
                .collect(Collectors.toList());
    }
}
