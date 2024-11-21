package com.eshopweb.service.impl;

import com.eshopweb.domain.Settings;
import com.eshopweb.repository.SettingsRepository;
import com.eshopweb.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Intellij Idea
 * Created by ivosahlik on 27/12/2018
 */
@RequiredArgsConstructor
@Service
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository settingsRepository;
    public final Settings save(Settings settings) {
        return settingsRepository.save(settings);
    }

    @Override
    public Settings findOne(Long id) {
        return settingsRepository.findOne(id);
    }

    @Override
    public List<Settings> findAll() {
        return settingsRepository.findAll();
    }

    @Override
    public void remove(long id) {
        settingsRepository.delete(id);
    }

    @Override
    public List<Settings> findOneActive() {
        return settingsRepository.findAll().stream()
                .filter(Settings::isActive)
                .limit(1)
                .collect(Collectors.toList());
    }
}
