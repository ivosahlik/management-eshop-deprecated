package com.eshopweb.repository;

import com.eshopweb.domain.VideoBanner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-10-13
 */
@Repository
public interface VideoRepository extends CrudRepository<VideoBanner, Integer> {


}
