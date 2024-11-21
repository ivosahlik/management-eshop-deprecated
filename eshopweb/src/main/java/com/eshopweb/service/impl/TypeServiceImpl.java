package com.eshopweb.service.impl;

import com.eshopweb.domain.Type;
import com.eshopweb.repository.TypeRepository;
import com.eshopweb.service.TypeService;
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
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll().stream()
                .filter(Type::isActive)
                .filter(type -> !Strings.isNullOrEmpty(type.getTypeTitlePath()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Type> findAllActive() {
        List<Type> typeList = typeRepository.findAll();
        return typeList.stream().filter(Type::isActive).collect(Collectors.toList());
    }

    @Override
    public Type findOne(Long id) {
        return typeRepository.findOne(id);
    }

    @Override
    public void removeType(Long id) {
        typeRepository.delete(id);
    }

    @Override
    public List<Type> findAllTypesBySubCategorySeo(String subCategory) {
        return typeRepository
                .findAllTypesBySubCategorySeo(subCategory)
                .stream()
                .filter(Type::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public Type findOneByTypeSeo(String typeSeo) {
        return typeRepository.findOneByTypeSeo(typeSeo);
    }
}
