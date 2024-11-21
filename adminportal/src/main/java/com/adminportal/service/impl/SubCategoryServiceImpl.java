package com.adminportal.service.impl;

import com.adminportal.domain.SubCategory;
import com.adminportal.repository.SubCategoryRepository;
import com.adminportal.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategory save(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public List<SubCategory> findAll() {
        return StreamSupport.stream(subCategoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubCategory> findAllActive() {
        return StreamSupport.stream(subCategoryRepository.findAll().spliterator(),false)
                .filter(SubCategory::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public SubCategory findOne(Long id) {
        return subCategoryRepository.findOne(id);
    }

    @Override
    public void removeCategory(Long id) {
        subCategoryRepository.delete(id);
    }

    @Override
    public boolean isSubCategoryName(String title) {
        return subCategoryRepository.isSubCategoryName(title)  > 0 ? true : false;
    }
}
