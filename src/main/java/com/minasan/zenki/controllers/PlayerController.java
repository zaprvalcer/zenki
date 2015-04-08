package com.minasan.zenki.controllers;

import com.minasan.zenki.models.*;
import com.minasan.zenki.services.MocksGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/data/player/{playerId}")
public class PlayerController {
    @Resource
    private MocksGeneratorService mocksGeneratorService;

    @RequestMapping(value = "/round", method = RequestMethod.GET)
    @ResponseBody
    public GameDistributionTO getDistribution(@PathVariable long playerId) {
        return mocksGeneratorService.getGameDistribution(playerId);
    }

    @RequestMapping(value = "/card/{cardId}/topic", method = RequestMethod.POST)
    @ResponseBody
    public TopicDistributionTO chooseTopic(@PathVariable long playerId, @PathVariable long cardId, @RequestBody String topic) {
        return mocksGeneratorService.chooseTopic(playerId, cardId, topic);
    }

    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    @ResponseBody
    public TopicDistributionTO getTopicDistribution() {
        return mocksGeneratorService.getTopicDistribution();
    }

    @RequestMapping(value = "/table_cards", method = RequestMethod.GET)
    @ResponseBody
    public TableCardsDistributionTO getMoveDistribution(@PathVariable long playerId) {
        return mocksGeneratorService.getTableCardsDistribution(playerId);
    }

    @RequestMapping(value = "/card/{cardId}/add", method = RequestMethod.POST)
    @ResponseBody
    public TableCardsDistributionTO addCard(@PathVariable long playerId, @PathVariable long cardId) {
        return mocksGeneratorService.addTableCard(playerId, cardId);
    }

    @RequestMapping(value = "/card/{cardId}/vote", method = RequestMethod.POST)
    @ResponseBody
    public VotesDistributionTO voteForCard(@PathVariable long playerId, @PathVariable long cardId) {
        return mocksGeneratorService.makeVote(playerId, cardId);
    }

    @RequestMapping(value = "/votes", method = RequestMethod.GET)
    @ResponseBody
    public VotesDistributionTO fetchVotes(@PathVariable long playerId) {
        return mocksGeneratorService.getVotes(playerId);
    }
}