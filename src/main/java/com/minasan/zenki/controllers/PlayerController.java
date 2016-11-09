package com.minasan.zenki.controllers;

import com.minasan.zenki.models.*;
import com.minasan.zenki.services.MocksGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/data/player")
public class PlayerController {
    @Resource
    private MocksGeneratorService mocksGeneratorService;


    @RequestMapping(value = "/{playerId}", method = RequestMethod.GET)
    @ResponseBody
    public UserTO getUserData(@PathVariable long playerId) {
        return mocksGeneratorService.getUserData(playerId);
    }

    @RequestMapping(value = "/{playerId}/distribution", method = RequestMethod.GET)
    @ResponseBody
    public DistributionTO getDistribution(@PathVariable long playerId) {
        return mocksGeneratorService.getDistribution(playerId);
    }

}