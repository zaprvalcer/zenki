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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public RoomContentTO registerNewPlayer(@RequestBody UserTO player) {
        return mocksGeneratorService.addUser(player);
    }

    @RequestMapping(value = "/{playerId}/distribution", method = RequestMethod.GET)
    @ResponseBody
    public DistributionTO getDistribution(@PathVariable long playerId) {
        return mocksGeneratorService.getDistribution(playerId);
    }

}