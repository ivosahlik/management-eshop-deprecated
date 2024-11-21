package com.adminportal.service;

import com.adminportal.domain.Support;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-03
 */
public interface SupportService {

    List<Support> getListSupport();

    void save(Support support);

    Support getSupportById(Integer id);
}
