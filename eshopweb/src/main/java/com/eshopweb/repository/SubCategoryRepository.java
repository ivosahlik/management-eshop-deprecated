package com.eshopweb.repository;

import com.eshopweb.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query("select i from SubCategory i where i.subCategorytitlePath = ?1")
    SubCategory findOneBySubCategorySeo(String subCategorySeo);

    @Query(value = "select s.* from sub_category s inner join category c on s.category_id = c.id " +
            "where c.category_title_path = ?1 and c.app like %?2%", nativeQuery = true)
    List<SubCategory> findAllSubCategoryByCategorySeo(String categorySeo, String app);


}
