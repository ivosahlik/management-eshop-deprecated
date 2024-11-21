package com.eshopweb.service.impl;

import com.eshopweb.domain.CustomSettings;
import com.eshopweb.repository.CustomSettingsRepository;
import com.eshopweb.service.CustomSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-06
 */
@RequiredArgsConstructor
@Service
public class CustomSettingsServiceImpl implements CustomSettingsService {

    private final CustomSettingsRepository customSettingsRepository;

    @Override
    public Optional<CustomSettings> getOneByActiveCustomSettings() {
        return StreamSupport.stream(customSettingsRepository.findAll().spliterator(), false)
                .filter(CustomSettings::isActive)
                .filter(customSettings -> customSettings.getApp().contains(DataSlowDataAccessor.getApp()))
                .findFirst();
    }
}
