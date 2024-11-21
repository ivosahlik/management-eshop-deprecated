package com.eshopweb.repository;

import com.eshopweb.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("select i from Category i where i.categoryTitlePath = ?1")
    Category findOneCategoryByPath(String path);

    @Query("select i from Category i where i.categoryTitlePath = ?1")
    Category findOneByCategorySeo(String categorySeo);

}
