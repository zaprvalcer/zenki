package com.minasan.zenki.controllers;

import com.minasan.zenki.services.MocksGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class ViewsController {
    @Resource
    private MocksGeneratorService mocksGeneratorService;


    @RequestMapping(value = "/mainpage", method = {RequestMethod.GET})
    public String greetUser(ModelMap model) {
        model.addAttribute("message", "wazup");
        return "mainpage";
    }
}
