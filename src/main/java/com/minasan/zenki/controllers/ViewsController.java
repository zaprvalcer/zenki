package com.minasan.zenki.controllers;

import com.minasan.zenki.models.GameDistributionTO;
import com.minasan.zenki.models.UserTO;
import com.minasan.zenki.services.MocksGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewsController {
    @Resource
    private MocksGeneratorService mocksGeneratorService;

    /**
     * Greetings page for unregistered user
     *
     * @param model page model
     * @return greetings page for unregistered user
     */
    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public String getAnonymousMainpage(ModelMap model) {
        return "mainpage";
    }

    /**
     * Registration API with redirect to mainpage
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return greetings page for registered user
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String newUserName = httpServletRequest.getParameter("userName");
        UserTO newUser = mocksGeneratorService.addUser(newUserName);
        return "redirect:/mainpage?pid="+newUser.getId();
    }

    /**
     * Greetings page for registered user
     *
     * @param model page model
     * @return greetings page for registered user
     */
    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String getRegisteredUserMainpage(ModelMap model, @RequestParam long pid) {
        UserTO userData = mocksGeneratorService.getUserData(pid);
        model.addAttribute("player", userData);
        return "mainpage";
    }

    /**
     * Gamescreen page for registered user
     *
     * @param model page model
     * @param pid player id
     * @return gamescreen page for registered user
     */
    @RequestMapping(value = "/gamescreen", method = RequestMethod.GET)
    public String getGameScreen(ModelMap model, @RequestParam long pid) {
        GameDistributionTO distribution = mocksGeneratorService.getGameDistribution(pid);
        model.addAttribute("distribution", distribution);
        return "gamescreen";
    }
}
