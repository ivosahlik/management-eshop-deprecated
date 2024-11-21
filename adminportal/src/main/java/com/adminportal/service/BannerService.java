package com.adminportal.service;

import com.adminportal.domain.Banners;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-02
 */
public interface BannerService {

    List<Banners> findAll();

    Banners getBanner(Integer id);

    void save(Banners banners);
}
