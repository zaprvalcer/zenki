package com.minasan.zenki.services;

import com.minasan.zenki.models.*;

public interface MocksGeneratorService {
    public RoomContentTO getRoomContent();

    public UserTO getUserData(long playerId);

    public UserTO addUser(String newUserName);

    public GameDistributionTO getGameDistribution(long playerId);

    public TopicDistributionTO getTopicDistribution();

    public TopicDistributionTO chooseTopic(long playerId, long cardId, String title);

    public TableCardsDistributionTO getTableCardsDistribution(long playerId);

    public TableCardsDistributionTO addTableCard(long playerId, long cardId);

    public VotesDistributionTO makeVote(long playerId, long cardId);

    public VotesDistributionTO getVotes(long playerId);
}
