package com.adminportal.repository;

import com.adminportal.domain.CustomSettings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-15
 */
@Repository
public interface CustomSettingsRepository extends CrudRepository<CustomSettings, Integer> {
}
