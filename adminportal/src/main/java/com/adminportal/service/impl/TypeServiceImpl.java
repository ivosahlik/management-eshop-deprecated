package com.adminportal.service.impl;

import com.adminportal.domain.Type;
import com.adminportal.repository.TypeRepository;
import com.adminportal.service.TypeService;
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
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public List<Type> findAll() {
        return StreamSupport.stream(typeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Type> findAllActive() {
        return StreamSupport.stream(typeRepository.findAll().spliterator(), false)
                .filter(Type::isActive)
                .collect(Collectors.toList());
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
    public boolean isTypeName(String type) {
        return typeRepository.isTypeName(type) > 0;
    }

}
