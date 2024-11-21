package com.eshopweb.repository;

import com.eshopweb.domain.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {

}
