package com.adminportal.controller;

import com.adminportal.service.RequestPricingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Intellij Idea
 * Created by ivosahlik on 07/01/2019
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class RequestPricingController {

    private final RequestPricingService requestPricingService;

    @GetMapping("/getAQoute")
    private String getAQouteList(Model model) {
        model.addAttribute("currentPage","requestPricing");
        model.addAttribute("getAQuoteList", requestPricingService.findAll());
        return "getAQuote/getAQuoteList";
    }
}
