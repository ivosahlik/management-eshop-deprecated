package com.adminportal.service.impl;

import com.adminportal.domain.Countries;
import com.adminportal.repository.CountriesRepository;
import com.adminportal.service.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-28
 */
@Service
@RequiredArgsConstructor
public class CountriesServiceImpl implements CountriesService {

    private final CountriesRepository countriesRepository;

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

    @Override
    public void remove(int id) {
        countriesRepository.delete(id);
    }
}
