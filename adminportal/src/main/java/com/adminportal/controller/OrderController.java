package com.adminportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-05-10
 */
@Controller
public class OrderController {

    @GetMapping("/orderlist")
    public String orderList(Model model) {

        model.addAttribute("currentPage","order");

        return "order/orderList";

    }

}
