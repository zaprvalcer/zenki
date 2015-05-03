package com.minasan.zenki.models;

public class UserCardTO {
    private CardTO card;
    private UserTO user;

    public CardTO getCard() {
        return card;
    }

    public void setCard(CardTO card) {
        this.card = card;
    }

    public UserTO getUser() {
        return user;
    }

    public void setUser(UserTO user) {
        this.user = user;
    }
}
