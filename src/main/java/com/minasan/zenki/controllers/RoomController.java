package com.minasan.zenki.controllers;

import com.minasan.zenki.models.RoomContentTO;
import com.minasan.zenki.models.TopicTO;
import com.minasan.zenki.models.VoteTO;
import com.minasan.zenki.models.VotesSummaryTO;
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

    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    @ResponseBody
    public TopicTO getTopic() {
        return mocksGeneratorService.getTopic();
    }

    @RequestMapping(value = "/topic", method = RequestMethod.POST)
    @ResponseBody
    public VotesSummaryTO setTopic(@RequestBody TopicTO topic) {
        return mocksGeneratorService.setTopic(topic);
    }

    @RequestMapping(value = "/votes", method = RequestMethod.GET)
    @ResponseBody
    public VotesSummaryTO getVotes() {
        return mocksGeneratorService.getVotes();
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    @ResponseBody
    public VotesSummaryTO makeVote(@RequestBody VoteTO vote) {
        return mocksGeneratorService.submitVote(vote);
    }

}
