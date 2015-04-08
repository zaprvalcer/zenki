package com.minasan.zenki.controllers;

import com.minasan.zenki.models.RoomContentTO;
import com.minasan.zenki.services.MocksGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/data/room")
public class RoomController {
    @Resource
    private MocksGeneratorService mocksGeneratorService;

    @RequestMapping(value = "/content", method = RequestMethod.GET)
    @ResponseBody
    public RoomContentTO getRoomContent() {
        return mocksGeneratorService.getRoomContent();
    }
}
