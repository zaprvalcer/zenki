package com.minasan.zenki.models;

public class TopicDistributionTO {
    private TopicTO topic;
    private PlayerStatusTO playerStatus;

    public TopicTO getTopic() {
        return topic;
    }

    public void setTopic(TopicTO topic) {
        this.topic = topic;
    }

    public PlayerStatusTO getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatusTO playerStatus) {
        this.playerStatus = playerStatus;
    }
}
