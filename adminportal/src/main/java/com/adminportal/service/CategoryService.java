package com.adminportal.service;

import com.adminportal.domain.Category;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

    List<Category> findAllActive();

    Category findOne(Long id);

    void removeCategory(Long id);

    boolean isCategoryName(String categoryTitle);

    void updateCategoryOrder(int categoryId, int order);

}
