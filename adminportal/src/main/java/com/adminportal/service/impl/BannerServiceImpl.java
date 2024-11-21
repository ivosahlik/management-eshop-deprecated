package com.adminportal.service.impl;

import com.adminportal.domain.Banners;
import com.adminportal.repository.BannerRepository;
import com.adminportal.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-02
 */
@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;


    @Override
    public List<Banners> findAll() {
        return StreamSupport.stream(bannerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Banners getBanner(Integer id) {
        return bannerRepository.findOne(id);
    }

    @Override
    public void save(Banners banners) {
        bannerRepository.save(banners);
    }
}
