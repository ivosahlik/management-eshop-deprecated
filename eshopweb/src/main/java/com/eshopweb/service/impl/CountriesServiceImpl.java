package com.eshopweb.service.impl;


import com.eshopweb.domain.Countries;
import com.eshopweb.repository.CountriesRepository;
import com.eshopweb.service.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-28
 */
@Service
public class CountriesServiceImpl implements CountriesService {

    @Autowired
    private CountriesRepository countriesRepository;

    @Autowired
    private DataSlowDataAccessor dataSlowDataAccessor;

    @Override
    public List<Countries> findAllByActive() {
        return StreamSupport.stream(countriesRepository.findAll().spliterator(), false)
                .filter(Countries::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Countries> findAll() {
        return StreamSupport.stream(countriesRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Countries> getCountryList() {
        return dataSlowDataAccessor.getCountriesList().stream()
                .filter(Countries::isActive)
                .sorted(Comparator.comparing(Countries::getCountry))
                .collect(Collectors.toList());
    }

}
