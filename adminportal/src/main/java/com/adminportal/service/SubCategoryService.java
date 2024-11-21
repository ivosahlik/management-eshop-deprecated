package com.adminportal.service;

import com.adminportal.domain.SubCategory;

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

    boolean isSubCategoryName(String title);

}
