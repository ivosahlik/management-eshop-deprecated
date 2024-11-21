package com.eshopweb.controller;

import com.eshopweb.domain.Support;
import com.eshopweb.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-13
 */
@Controller
public class AboutController {

    @Autowired
    private SupportService supportService;

    @GetMapping("/aboutUs")
    public String aboutUs() {


        return "about";
    }

    @GetMapping("/support")
    public String support(Model model) {
        Optional<Support> optionalSupport = supportService.getOneActiveSupport();
        optionalSupport.ifPresent(support -> model.addAttribute("support", support));
        return "support/about";
    }

}
