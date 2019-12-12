package com.dxm.anymock.manager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

    @RequestMapping("/")
    public String redirect() {
        return "redirect:/fe/index.html";
    }
}
