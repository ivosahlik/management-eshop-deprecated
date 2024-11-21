package com.adminportal.controller;

import com.adminportal.domain.Support;
import com.adminportal.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-03
 */
@RequestMapping("/support")
@RequiredArgsConstructor
@Controller
public class SupportController {

    private final SupportService supportService;

    @GetMapping("/list")
    public String supportList(Model model) {
        List<Support> supportList = supportService.getListSupport();
        model.addAttribute("supportList", supportList);
        return "support/list";
    }

    @GetMapping("/add")
    public String supportAdd(Model model) {
        Support support = new Support();
        model.addAttribute("support", support);
        return "support/add";
    }

    @GetMapping("/edit")
    public String getSupportById(@RequestParam("id") Integer id, Model model) {
        Support support = supportService.getSupportById(id);

        model.addAttribute("support", supportService.getSupportById(id));
        if (support.getApp() != null && support.getApp().contains("membrania")) {
            model.addAttribute("membrania", Boolean.TRUE);
        }

        if (support.getApp() != null && support.getApp().contains("dcsolutions")) {
            model.addAttribute("dcsolutions", Boolean.TRUE);
        }
        return "support/edit";
    }

    @GetMapping("/info/{id}")
    public String getSupportInfoById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("support", supportService.getSupportById(id));
        return "support/info";
    }

    @PostMapping("/add")
    public String supportAddPost(@ModelAttribute("support") Support support,
                                 @RequestParam(value = "apps", required = false) String[] apps,
                                 Principal principal) {

        if (support.getId() != null) {
            support.setId(support.getId());
            support.setUpdated(LocalDateTime.now());
        } else {
            support.setCreated(LocalDateTime.now());
        }

        support.setCreated(LocalDateTime.now());
        support.setUserCreated(principal.getName());

        if (apps != null) {
            support.setApp(String.join(",", apps));
        } else {
            support.setApp("");
        }

        supportService.save(support);

        return "redirect:/support/list";
    }


}
