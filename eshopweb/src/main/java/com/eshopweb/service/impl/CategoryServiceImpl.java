package com.eshopweb.service.impl;

import com.eshopweb.domain.Category;
import com.eshopweb.repository.CategoryRepository;
import com.eshopweb.service.CategoryService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll().stream()
                .filter(Category::isActive)
                .filter(category -> !Strings.isNullOrEmpty(category.getCategoryTitlePath()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> findAllByApp(String app) {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getApp() != null)
                .filter(category -> category.getApp().contains(app) && category.isActive())
                .filter(category -> !Strings.isNullOrEmpty(category.getCategoryTitlePath()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> findAllActive() {
        return categoryRepository.findAll().stream()
                .filter(Category::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void removeCategory(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public boolean isCategoryActive(Long id) {
        return categoryRepository.findOne(id).isActive();
    }

    @Override
    public Category getCategoryActive(String path) {
        return categoryRepository.findOneCategoryByPath(path);
    }

    @Override
    public Category findOneByCategorySeo(String categorySeo) {
        return categoryRepository.findOneByCategorySeo(categorySeo);
    }

}
