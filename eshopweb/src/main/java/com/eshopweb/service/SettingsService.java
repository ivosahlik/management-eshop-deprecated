package com.eshopweb.service;


import com.eshopweb.domain.Settings;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 27/12/2018
 */
public interface SettingsService {

    Settings save(Settings settings);

    Settings findOne(Long id);

    List<Settings> findAll();

    void remove(long id);

    List<Settings> findOneActive();


}
