package com.adminportal.service;

import com.adminportal.domain.Countries;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-28
 */
public interface CountriesService {

    List<Countries> findAllByActive();

    List<Countries> findAll();

    void remove(int id);
}
