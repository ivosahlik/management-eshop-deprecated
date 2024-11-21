package com.eshopweb.repository;

import com.eshopweb.domain.dto.ProductToCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-01-13
 */
public interface ProductToCategoryRepossitory extends JpaRepository<ProductToCategoryDto, Long> {


}
