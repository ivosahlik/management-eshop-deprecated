package com.eshopweb.service.impl;

import com.eshopweb.domain.SubCategory;
import com.eshopweb.repository.SubCategoryRepository;
import com.eshopweb.service.SubCategoryService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RequiredArgsConstructor
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategory save(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public List<SubCategory> findAll() {
        return subCategoryRepository.findAll().stream()
                .filter(SubCategory::isActive)
                .filter(subCategory -> !Strings.isNullOrEmpty(subCategory.getSubCategorytitlePath()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SubCategory> findAllActive() {
        return subCategoryRepository.findAll().stream()
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
    public SubCategory findOneBySubCategorySeo(String subCategorySeo) {
        return subCategoryRepository.findOneBySubCategorySeo(subCategorySeo);
    }

    @Override
    public List<SubCategory> findAllSubCategoryByCategorySeo(String categorySeo,
                                                             String app) {
        return subCategoryRepository
                .findAllSubCategoryByCategorySeo(categorySeo, app)
                .stream()
                .filter(SubCategory::isActive)
                .collect(Collectors.toList());
    }
}
