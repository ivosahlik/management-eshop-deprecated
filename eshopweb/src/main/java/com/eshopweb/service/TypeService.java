package com.eshopweb.service;

import com.eshopweb.domain.Type;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface TypeService {

    Type save(Type type);

    List<Type> findAll();

    List<Type> findAllActive();

    Type findOne(Long id);

    void removeType(Long id);

    List<Type> findAllTypesBySubCategorySeo(String subCategory);

    Type findOneByTypeSeo(String typeSeo);
}
