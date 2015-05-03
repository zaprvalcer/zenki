package com.minasan.zenki.models;

import java.util.List;

public class PlayerTO {
    private UserTO data;
    private PlayerStatusTO status;
    private List<CardTO> cards;

    public List<CardTO> getCards() {
        return cards;
    }

    public void setCards(List<CardTO> cards) {
        this.cards = cards;
    }

    public PlayerStatusTO getStatus() {
        return status;
    }

    public void setStatus(PlayerStatusTO status) {
        this.status = status;
    }

    public UserTO getData() {
        return data;
    }

    public void setData(UserTO data) {
        this.data = data;
    }
}
