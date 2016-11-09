package com.minasan.zenki.services.impl;

import com.minasan.zenki.models.*;
import com.minasan.zenki.services.MocksGeneratorService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

@Service
public class MocksGeneratorServiceImpl implements MocksGeneratorService {

    private static final long RIVAL1_ID = 111l;
    private static final long RIVAL2_ID = 222l;
    private static final long RIVAL3_ID = 333l;
    private static final int PLAYERS_LIMIT = 4;

    private RoomContentTO roomContent = new RoomContentTO();
    private TopicTO topic = null;

    public MocksGeneratorServiceImpl() {
        roomContent.setIsRoomFull(false);
        roomContent.setUsers(generateRivals());
    }

    @Override
    public UserTO getUserData(long playerId) {
        for(UserTO user : roomContent.getUsers()) {
            if(user.getId() == playerId) {
                return user;
            }
        }
        return null;
    }

    @Override
    public PlayerTO getPlayerData(long playerId) {
        PlayerTO player = new PlayerTO();
        player.setId(playerId);
        player.setName("Izya");

        Set<CardTO> cards = new HashSet<>();
        for(long index = 4l; index<11l; index++) {
            cards.add(generateCard(index));
        }
        player.setCards(cards);
        player.setColorSchema(ColorSchemaTO.BLUE);
        player.setStatus(PlayerStatusTO.WAIT_PARTY);

        return player;
    }

    @Override
    public VotesSummaryTO submitVote(VoteTO vote) {
        System.out.println("Vote is made by " + vote.getPlayerId() + " for card " + vote.getCardId());
        UserRateTO rate = generateRate(vote.getPlayerId(), 10);

        VotesSummaryTO votesSummary = getVotes();
        votesSummary.getVotes().add(vote);
        votesSummary.getRates().add(rate);
        votesSummary.setIsFinished(true);
        return votesSummary;
    }

    @Override
    public TopicTO getTopic() {
        return topic;
    }

    @Override
    public VotesSummaryTO getVotes() {
        List<VoteTO> votes = new ArrayList<>();
        votes.add(generateVote(RIVAL1_ID, 888l));
        votes.add(generateVote(RIVAL2_ID, 666l));
        votes.add(generateVote(RIVAL3_ID, 555l));

        List<UserRateTO> rates = new ArrayList<>();
        rates.add(generateRate(RIVAL1_ID, 10));
        rates.add(generateRate(RIVAL2_ID, 5));
        rates.add(generateRate(RIVAL3_ID, 13));

        VotesSummaryTO votesSummary = new VotesSummaryTO();
        votesSummary.setIsFinished(false);
        votesSummary.setVotes(votes);
        votesSummary.setRates(rates);
        return votesSummary;
    }

    @Override
    public VotesSummaryTO setTopic(TopicTO topic) {
        System.out.println("Set topic "+topic.getTitle());
        return new VotesSummaryTO();
    }

    @Override
    public DistributionTO getDistribution(long playerId) {
        DistributionTO distribution = new DistributionTO();
        distribution.setPlayer(getPlayerData(playerId));
        distribution.setParty(generateRivals());
        return distribution;
    }

    @Override
    public UserTO addUser(String newUserName) {
        System.out.println("Added new user "+newUserName);
        List<UserTO> users = roomContent.getUsers();
        UserTO lastUser = users.get(users.size()-1);

        long newIndex = lastUser.getId()+1;
        UserTO newUser = generateUser(newIndex, newUserName, ColorSchemaTO.BLUE);

        users.add(newUser);

        if(users.size() >= PLAYERS_LIMIT) {
            roomContent.setIsRoomFull(true);
        }
        return newUser;
    }

    @Override
    public RoomContentTO getRoomContent() {
        return roomContent;
    }

    private CardTO generateCard(long cardId) {
        CardTO card = new CardTO();
        card.setId(cardId);
        return card;
    }

    private VoteTO generateVote(long playerId, long cardId) {
        VoteTO vote = new VoteTO();
        vote.setCardId(cardId);
        vote.setPlayerId(playerId);
        return vote;
    }

    private UserRateTO generateRate(long playerId, int score) {
        UserRateTO userRate = new UserRateTO();
        userRate.setId(playerId);
        userRate.setScore(score);
        return userRate;
    }

    private List<UserTO> generateRivals() {
        List<UserTO> rivals = new ArrayList<>();
        rivals.add(generateUser(RIVAL1_ID, "Bruce", ColorSchemaTO.RED));
        rivals.add(generateUser(RIVAL2_ID, "Lee", ColorSchemaTO.GREEN));
        return rivals;
    }
    private UserTO generateUser(long rivalId, String name, ColorSchemaTO schema) {
        UserTO rival = new UserTO();
        rival.setId(rivalId);
        rival.setName(name);
        rival.setColorSchema(schema);
        return rival;
    }
}
