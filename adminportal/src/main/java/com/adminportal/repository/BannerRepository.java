package com.adminportal.repository;

import com.adminportal.domain.Banners;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-02
 */
@Repository
public interface BannerRepository extends CrudRepository<Banners, Integer> {

}
