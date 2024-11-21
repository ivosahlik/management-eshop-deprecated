package com.adminportal.repository;

import com.adminportal.domain.Countries;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-28
 */
@Repository
public interface CountriesRepository extends CrudRepository<Countries, Integer> {


}
