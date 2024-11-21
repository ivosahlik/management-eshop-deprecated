package com.adminportal.service.impl;

import com.adminportal.domain.VideoBanner;
import com.adminportal.repository.VideoBannerRepository;
import com.adminportal.service.VideoBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-10-28
 */
@Service
public class VideoBannerServiceImpl implements VideoBannerService {

    @Autowired
    private VideoBannerRepository videoBannerRepository;

    @Override
    public List<VideoBanner> getVideoBanner() {
        return StreamSupport.stream(videoBannerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public VideoBanner save(VideoBanner videoBanner) {
        return videoBannerRepository.save(videoBanner);
    }

    @Override
    public Object getVideoBannerById(Integer id) {
        return videoBannerRepository.findOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        videoBannerRepository.delete(id);
    }
}
