package com.eshopweb.repository;

import com.eshopweb.domain.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Intellij Idea
 * Created by ivosahlik on 27/12/2018
 */
public interface SettingsRepository extends JpaRepository<Settings, Long> {



}
