package com.adminportal.repository;

import com.adminportal.domain.TariffZone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
@Repository
public interface TariffZoneRepository extends CrudRepository<TariffZone, Integer> {

}
