package com.eshopweb.repository;

import com.eshopweb.domain.DhlTransport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
@Repository
public interface DhlTransportRepository extends CrudRepository<DhlTransport, Integer> {

    @Query("select i from DhlTransport i where i.weight = ?1")
    DhlTransport findOneByWight(double grandWidthTotal);
}
