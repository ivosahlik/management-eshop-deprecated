package com.adminportal.controller;

import com.adminportal.service.CountriesService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-05-01
 */
@RequiredArgsConstructor
@RequestMapping("/countries")
@Controller
public class CountriesController {

    private static final String REDIRECT_COUNTRIES_LIST = "redirect:/countries/list";
    private static final String REDIRECT = "redirect:/";

    private final CountriesService countriesService;

    @GetMapping("/list")
    public String countriesList(Model model) {
        model.addAttribute("countriesList", countriesService.findAll());
        model.addAttribute("currentPage","countries");
        return "countries/countriesList";
    }

    @PostMapping("/removeCountry")
    public String removeCountry(@ModelAttribute("id") String id, Model model) {
        if (Strings.isNullOrEmpty(id)) {
            return REDIRECT;
        }
        countriesService.remove(Integer.parseInt(id.substring(11)));
        model.addAttribute("countriesList", countriesService.findAll());

        return REDIRECT_COUNTRIES_LIST;
    }

}
