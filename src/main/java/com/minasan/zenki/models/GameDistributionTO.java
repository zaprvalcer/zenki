package com.minasan.zenki.models;

import java.util.List;

public class GameDistributionTO {
    private TopicTO topic;
    private PlayerTO player;
    private List<UserTO> party;

    public List<UserTO> getParty() {
        return party;
    }

    public void setParty(List<UserTO> party) {
        this.party = party;
    }

    public PlayerTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerTO player) {
        this.player = player;
    }

    public TopicTO getTopic() {
        return topic;
    }

    public void setTopic(TopicTO topic) {
        this.topic = topic;
    }
}
