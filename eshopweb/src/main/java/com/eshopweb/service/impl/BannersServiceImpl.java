package com.eshopweb.service.impl;

import com.eshopweb.domain.Banners;
import com.eshopweb.repository.BannerRepository;
import com.eshopweb.service.BannersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-02
 */
@Service
public class BannersServiceImpl implements BannersService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banners getBanner(Integer bannerId) {
        return bannerRepository.findOne(bannerId);
    }
}
