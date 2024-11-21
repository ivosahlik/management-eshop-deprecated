package com.adminportal.repository;

import com.adminportal.domain.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(value = "select count(*) from category c where c.title = ?1", nativeQuery = true)
    Integer isCategoryName(String categoryTitle);


    @Transactional
    @Modifying
    @Query(value = "update category c set c.order_by =:order where c.id =:categoryId", nativeQuery = true)
    void updateCategoryOrder(@Param("categoryId") int categoryId, @Param("order") int order);

}
