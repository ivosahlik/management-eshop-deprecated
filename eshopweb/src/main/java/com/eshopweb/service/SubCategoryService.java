package com.eshopweb.service;

import com.eshopweb.domain.SubCategory;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface SubCategoryService {

    SubCategory save(SubCategory subCategory);

    List<SubCategory> findAll();

    List<SubCategory> findAllActive();

    SubCategory findOne(Long id);

    void removeCategory(Long id);

    SubCategory findOneBySubCategorySeo(String subCategorySeo);

    List<SubCategory> findAllSubCategoryByCategorySeo(String categorySeo, String app);
}
