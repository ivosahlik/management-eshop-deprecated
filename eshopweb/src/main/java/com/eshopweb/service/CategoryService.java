package com.eshopweb.service;

import com.eshopweb.domain.Category;
import com.eshopweb.domain.SubCategory;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

    List<Category> findAllByApp(String app);

    List<Category> findAllActive();

    Category findOne(Long id);

    void removeCategory(Long id);

    boolean isCategoryActive(Long id);

    Category getCategoryActive(String path);

    Category findOneByCategorySeo(String categorySeo);

}
