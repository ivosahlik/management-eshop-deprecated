package com.adminportal.repository;

import com.adminportal.domain.SubCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {

    @Query(value = "select count(*) from sub_category s where s.title = ?1", nativeQuery = true)
    Integer isSubCategoryName(String title);

}
