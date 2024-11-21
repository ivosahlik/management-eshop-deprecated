package com.eshopweb.repository;

import com.eshopweb.domain.CustomSettings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-06
 */@Repository
public interface CustomSettingsRepository extends CrudRepository<CustomSettings, Integer> {



}
