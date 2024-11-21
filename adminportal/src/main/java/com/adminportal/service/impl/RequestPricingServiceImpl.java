package com.adminportal.service.impl;

import com.adminportal.domain.RequestPricing;
import com.adminportal.repository.RequestPricingRepository;
import com.adminportal.service.RequestPricingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Intellij Idea
 * Created by ivosahlik on 06/01/2019
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RequestPricingServiceImpl implements RequestPricingService {

    private final RequestPricingRepository requestPricingRepository;

    @Override
    public RequestPricing save(RequestPricing requestPricing) {
        return requestPricingRepository.save(requestPricing);
    }

    @Override
    public List<RequestPricing> findAll() {
        return requestPricingRepository.findAll();
    }
}
