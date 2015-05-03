package com.minasan.zenki.models;

import java.util.List;
import java.util.Map;

public class VotesDistributionTO {
    private PlayerStatusTO playerStatus;
    private List<UserTO> readyRivals;
    private Map<Long, VoteTO> votesPerCard;

    public PlayerStatusTO getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatusTO playerStatus) {
        this.playerStatus = playerStatus;
    }

    public List<UserTO> getReadyRivals() {
        return readyRivals;
    }

    public void setReadyRivals(List<UserTO> readyRivals) {
        this.readyRivals = readyRivals;
    }

    public Map<Long, VoteTO> getVotesPerCard() {
        return votesPerCard;
    }

    public void setVotesPerCard(Map<Long, VoteTO> votesPerCard) {
        this.votesPerCard = votesPerCard;
    }
}
