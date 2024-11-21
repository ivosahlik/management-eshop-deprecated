package com.adminportal.service.impl;

import com.adminportal.domain.Category;
import com.adminportal.repository.CategoryRepository;
import com.adminportal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> findAllActive() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
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
    public boolean isCategoryName(String categoryTitle) {
        return categoryRepository.isCategoryName(categoryTitle) > 0;
    }

    @Override
    public void updateCategoryOrder(int categoryId, int order) {
        categoryRepository.updateCategoryOrder(categoryId, order);
    }
}
