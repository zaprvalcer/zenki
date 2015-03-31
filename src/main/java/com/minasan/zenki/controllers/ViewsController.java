package com.minasan.zenki.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewsController {

    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String greetUser(ModelMap model) {
        model.addAttribute("message", "wazup");
        return "mainpage";
    }
}
