package com.adminportal.service;

import com.adminportal.domain.TariffZone;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
public interface TariffZoneService {

    TariffZone save(TariffZone tariffZone);

    List<TariffZone> findAll();

    void removeTariffZone(Integer id);
}
