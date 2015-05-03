package com.minasan.zenki.models;

public class VoteTO {
    private UserTO owner;
    private UserTO voter;
    private int ownerBonus;
    private int voterBonus;

    public UserTO getOwner() {
        return owner;
    }

    public void setOwner(UserTO owner) {
        this.owner = owner;
    }

    public UserTO getVoter() {
        return voter;
    }

    public void setVoter(UserTO voter) {
        this.voter = voter;
    }

    public int getOwnerBonus() {
        return ownerBonus;
    }

    public void setOwnerBonus(int ownerBonus) {
        this.ownerBonus = ownerBonus;
    }

    public int getVoterBonus() {
        return voterBonus;
    }

    public void setVoterBonus(int voterBonus) {
        this.voterBonus = voterBonus;
    }
}
