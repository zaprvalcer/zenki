package com.minasan.zenki.models;

import java.util.List;

public class DistributionTO {
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
}
