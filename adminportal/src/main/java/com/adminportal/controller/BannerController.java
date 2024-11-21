package com.adminportal.controller;

import com.adminportal.domain.Banners;
import com.adminportal.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-10-18
 */
@RequiredArgsConstructor
@RequestMapping("/banner")
@Controller
public class BannerController {

    private final BannerService bannerService;

    @GetMapping("/list")
    public String getBannerList(Model model) {
        List<Banners> bannersList = bannerService.findAll();
        model.addAttribute("bannerList", bannersList);
        return "banners/banner/list";
    }

    @GetMapping("/list/detail")
    public String getBannerDetail() {
        return "";
    }

    @GetMapping("/add")
    public String getBannerAdd(Model model) {
        Banners banners = new Banners();
        model.addAttribute("banner", banners);
        return "banners/banner/add";
    }

    @GetMapping("/edit/{id}")
    public String getBannerEditById(@PathVariable(value = "id") Integer id,
                                    Model model) {
        Banners banners = bannerService.getBanner(id);
        model.addAttribute("banner", banners);
        return "banners/banner/edit";
    }

    @PostMapping("/add")
    public String getBannerAdd(@ModelAttribute("banner") Banners banners,
                               Principal principal) {
        if (banners.getId() != null) {
            banners.setId(banners.getId());
        }
        banners.setCreated(LocalDateTime.now());
        banners.setUserCreated(principal.getName());
        bannerService.save(banners);
        return "redirect:/banner/list";
    }

    @DeleteMapping("/delete")
    public String getBannerDelele() {
        return "";
    }

}
