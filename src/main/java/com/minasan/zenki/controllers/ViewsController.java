package com.minasan.zenki.controllers;

import com.minasan.zenki.models.DistributionTO;
import com.minasan.zenki.models.RoomContentTO;
import com.minasan.zenki.models.UserTO;
import com.minasan.zenki.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewsController {
    @Resource
    private MocksGeneratorService mocksGeneratorService;

    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {
        return "mainpage";
    }

    @RequestMapping(value = "/mainpage", method = RequestMethod.POST)
    public ModelAndView registerUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String newUserName = httpServletRequest.getParameter("userName");
        UserTO newUser = mocksGeneratorService.addUser(newUserName);

        ModelAndView modelAndView = new ModelAndView("mainpage");
        modelAndView.addObject("player", newUser);
        return modelAndView;
    }

    @RequestMapping(value = "/game/{pid}", method = RequestMethod.GET)
    public String getGameScreen(ModelMap model, @PathVariable long pid) {
        DistributionTO distribution = mocksGeneratorService.getDistribution(pid);
        model.addAttribute("distribution", distribution);
        return "gamescreen";
    }
}
