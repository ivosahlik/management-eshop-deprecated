package com.eshopweb.service.impl;

import com.eshopweb.domain.RequestPricing;
import com.eshopweb.repository.RequestPricingRepository;
import com.eshopweb.service.RequestPricingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Intellij Idea
 * Created by ivosahlik on 06/01/2019
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RequestPricingServiceImpl implements RequestPricingService {

    private final RequestPricingRepository requestPricingRepository;

    @Override
    public RequestPricing save(RequestPricing requestPricing) {
        return requestPricingRepository.save(requestPricing);
    }
}
