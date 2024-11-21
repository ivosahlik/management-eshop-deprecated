package com.adminportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-08-10
 */
@RequestMapping("user")
@Controller
public class UserController {

    @GetMapping("/list")
    public String listUsers() {

        return "user/userList";
    }

}
