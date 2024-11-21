package com.adminportal.repository;

import com.adminportal.domain.Support;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-03
 */
@Repository
public interface SupportRrepository extends CrudRepository<Support, Integer> {
}
