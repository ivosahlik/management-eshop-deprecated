package com.eshopweb.repository;

import com.eshopweb.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-12
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

}
