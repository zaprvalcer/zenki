package com.minasan.zenki.services;

import com.minasan.zenki.models.*;

public interface MocksGeneratorService {

    public PlayerTO getPlayerData(long playerId);

    public VotesSummaryTO submitVote(VoteTO vote);

    public VotesSummaryTO getVotes();

    public VotesSummaryTO setTopic(TopicTO topic);

    public TopicTO getTopic();

    public DistributionTO getDistribution(long playerId);

    public RoomContentTO addUser(UserTO player);

    public RoomContentTO getRoomContent();
}
