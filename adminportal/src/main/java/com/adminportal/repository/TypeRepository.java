package com.adminportal.repository;

import com.adminportal.domain.SubCategory;
import com.adminportal.domain.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface TypeRepository extends CrudRepository<Type, Long> {

    @Query(value = "select count(*) from type t where t.title = ?1", nativeQuery = true)
    Integer isTypeName(String type);

}
