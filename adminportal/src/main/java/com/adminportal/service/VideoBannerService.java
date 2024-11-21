package com.adminportal.service;

import com.adminportal.domain.VideoBanner;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-10-28
 */
public interface VideoBannerService {

    List<VideoBanner> getVideoBanner();

    VideoBanner save(VideoBanner videoBanner);

    Object getVideoBannerById(Integer id);

    void deleteById(Integer id);
}
