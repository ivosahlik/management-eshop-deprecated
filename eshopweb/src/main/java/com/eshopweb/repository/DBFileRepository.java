package com.eshopweb.repository;

import com.eshopweb.domain.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-02-25
 */
public interface DBFileRepository extends JpaRepository<DBFile, String> {

    @Query(value = "select * from files i where i.product_id = ?1", nativeQuery = true)
    List<DBFile> findAllDBFilesByProductId(Long productId);

    @Query(value = "select * from files i where i.file_name = ?1", nativeQuery = true)
    DBFile findOneByName(String fileName);

}
