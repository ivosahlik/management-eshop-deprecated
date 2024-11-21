package com.eshopweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-13
 */
@Controller
public class GdprController {

    @GetMapping("/gdpr")
    public String gdpr() {


        return "gdpr/gdpr";
    }

}
