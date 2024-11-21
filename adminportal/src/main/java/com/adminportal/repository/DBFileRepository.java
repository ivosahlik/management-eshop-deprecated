package com.adminportal.repository;

import com.adminportal.domain.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

    @Query(value = "select count(*) from files p where p.file_name = ?1", nativeQuery = true)
    Integer isFilesDuplicate(String productName);

    @Query(value = "select * from files p where p.product_id = ?1", nativeQuery = true)
    List<DBFile> findOneById(Long id);
}
