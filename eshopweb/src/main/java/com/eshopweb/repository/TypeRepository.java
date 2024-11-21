package com.eshopweb.repository;

import com.eshopweb.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query(value = "select p.* from type p inner join sub_category s on p.sub_category_id = s.id " +
            "where s.sub_categorytitle_path = ?1", nativeQuery = true)
    List<Type> findAllTypesBySubCategorySeo(String subCategory);

    @Query("select i from Type i where i.typeTitlePath = ?1")
    Type findOneByTypeSeo(String typeSeo);
}
