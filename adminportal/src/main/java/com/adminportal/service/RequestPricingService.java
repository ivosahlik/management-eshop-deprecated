package com.adminportal.service;

import com.adminportal.domain.RequestPricing;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 06/01/2019
 */
public interface RequestPricingService {

    RequestPricing save(RequestPricing requestPricing);

    List<RequestPricing> findAll();
}
