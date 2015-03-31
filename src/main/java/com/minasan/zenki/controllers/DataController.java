package com.minasan.zenki.controllers;

import com.minasan.zenki.models.RoomStatusModel;
import com.minasan.zenki.services.MocksGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {
    @Resource
    private MocksGeneratorService mocksGeneratorService;

    @RequestMapping(value = "/mock", method = {RequestMethod.GET})
    @ResponseBody
    public List<String> getList() {
        return Arrays.asList("just", "mock");
    }

    @RequestMapping(value = "/roomStatus", method = {RequestMethod.GET})
    @ResponseBody
    public RoomStatusModel getRoomStatus() {
        return mocksGeneratorService.generateRoomStatus();
    }
}