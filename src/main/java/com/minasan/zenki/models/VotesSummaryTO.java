package com.minasan.zenki.models;

import java.util.List;

public class VotesSummaryTO {
    private boolean isFinished;
    private List<VoteTO> votes;
    private List<UserRateTO> rates;

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public List<UserRateTO> getRates() {
        return rates;
    }

    public void setRates(List<UserRateTO> rates) {
        this.rates = rates;
    }

    public List<VoteTO> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteTO> votes) {
        this.votes = votes;
    }
}
