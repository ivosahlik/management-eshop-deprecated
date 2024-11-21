package com.adminportal.repository;

import com.adminportal.domain.VideoStreams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-10-28
 */
@Repository
public interface VideoStreamRepository extends CrudRepository<VideoStreams, Integer> {
}
