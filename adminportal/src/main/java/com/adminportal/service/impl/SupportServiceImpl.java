package com.adminportal.service.impl;

import com.adminportal.domain.Support;
import com.adminportal.repository.SupportRrepository;
import com.adminportal.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-03
 */
@Service
public class SupportServiceImpl implements SupportService {

    @Autowired
    private SupportRrepository supportRrepository;

    @Override
    public List<Support> getListSupport() {
        return StreamSupport.stream(supportRrepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Support support) {
        supportRrepository.save(support);
    }

    @Override
    public Support getSupportById(Integer id) {
        return supportRrepository.findOne(id);
    }
}
