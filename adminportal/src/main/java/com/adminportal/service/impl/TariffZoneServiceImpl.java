package com.adminportal.service.impl;

import com.adminportal.domain.TariffZone;
import com.adminportal.repository.TariffZoneRepository;
import com.adminportal.service.TariffZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
@Service
public class TariffZoneServiceImpl implements TariffZoneService {

    @Autowired
    private TariffZoneRepository tariffZoneRepository;

    @Override
    public TariffZone save(TariffZone tariffZone) {
        return tariffZoneRepository.save(tariffZone);
    }

    @Override
    public List<TariffZone> findAll() {
        return StreamSupport.stream(tariffZoneRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(TariffZone::getCountry))
                .collect(Collectors.toList());
    }

    @Override
    public void removeTariffZone(Integer id) {
        tariffZoneRepository.delete(id);
    }

}
