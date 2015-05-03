package com.minasan.zenki.models;

import java.util.List;

public class TableCardsDistributionTO {
    private List<CardTO> tableCards;
    private List<UserTO> readyRivals;
    private PlayerStatusTO playerStatus;

    public PlayerStatusTO getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatusTO playerStatus) {
        this.playerStatus = playerStatus;
    }

    public List<CardTO> getTableCards() {
        return tableCards;
    }

    public void setTableCards(List<CardTO> tableCards) {
        this.tableCards = tableCards;
    }

    public List<UserTO> getReadyRivals() {
        return readyRivals;
    }

    public void setReadyRivals(List<UserTO> readyRivals) {
        this.readyRivals = readyRivals;
    }
}
