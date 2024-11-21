package com.eshopweb.repository;

import com.eshopweb.domain.TariffZone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
@Repository
public interface TariffZoneRepository extends CrudRepository<TariffZone, Integer> {

    @Query(value = "select i.zone from tariff_zone i where i.country = ?1", nativeQuery = true)
    Integer findTariffZoneByCountry(String country);
}
