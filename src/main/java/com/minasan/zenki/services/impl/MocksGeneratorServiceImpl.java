package com.minasan.zenki.services.impl;

import com.minasan.zenki.models.*;
import com.minasan.zenki.services.MocksGeneratorService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Mocks for one full round imitation
 */
@Service
public class MocksGeneratorServiceImpl implements MocksGeneratorService {

    private static final int PLAYERS_LIMIT = 2;
    private static final int CARDS_LIMIT = 6;

    private static final long PLAYER_START_ID = 111l;
    private static final int START_MOVE_INDEX = 0;

    private final Random randomGenerator = new Random();
    private final Map<Long, PlayerFlowMock> playersFlows = new HashMap<>();

    private final TopicTO topic = new TopicTO();
    private final Map<Long, VoteTO> votes = new HashMap<>();
    private final List<UserCardTO> tableCards = new ArrayList<>();

    @Override
    public TopicDistributionTO getTopicDistribution() {
        PlayerStatusTO playerStatus = (topic.getTitle()==null ? PlayerStatusTO.WAIT_USER_TOPIC : PlayerStatusTO.ADD_CARD);
        TopicDistributionTO distribution = new TopicDistributionTO();
        distribution.setTopic(topic);
        distribution.setPlayerStatus(playerStatus);
        return distribution;
    }

    @Override
    public UserTO getUserData(long playerId) {
        return playersFlows.get(playerId).userData;
    }

    @Override
    public RoomContentTO getRoomContent() {
        RoomStatusTO roomStatus;
        if(playersFlows.isEmpty()) {
            roomStatus = RoomStatusTO.EMPTY;
        } else if(playersFlows.size() >= PLAYERS_LIMIT) {
            roomStatus = RoomStatusTO.CROWDED;
        } else {
            roomStatus = RoomStatusTO.INHABITED;
        }

        RoomContentTO roomContent = new RoomContentTO();
        roomContent.setParticipants(getParticipants());
        roomContent.setRoomStatus(roomStatus);
        return roomContent;
    }


    @Override
    public UserTO addUser(String newUserName) {
        long newUserId = PLAYER_START_ID;
        if(!playersFlows.isEmpty()) {
            newUserId += playersFlows.size();
        }

        ColorSchemaTO colorSchema = generateRandomColorSchema();
        UserTO newUser = generateUser(newUserId, newUserName, colorSchema);
        PlayerFlowMock playerFlowMock = generatePlayerMock(playersFlows.size(), newUser);

        playersFlows.put(newUserId, playerFlowMock);
        return newUser;
    }

    @Override
    public GameDistributionTO getGameDistribution(long playerId) {
        List<UserTO> rivals = new ArrayList<>();
        for(PlayerFlowMock playerFlow : playersFlows.values()) {
            if(playerFlow.userData.getId() != playerId) {
                rivals.add(playerFlow.userData);
            }
        }

        GameDistributionTO distribution = new GameDistributionTO();
        distribution.setPlayer(getPlayerData(playerId));
        distribution.setParty(rivals);
        distribution.setTopic(topic);
        return distribution;
    }

    @Override
    public TableCardsDistributionTO getTableCardsDistribution(long playerId) {
        TableCardsDistributionTO moveDistribution = new TableCardsDistributionTO();
        PlayerFlowMock playerFlow = playersFlows.get(playerId);

        PlayerStatusTO playerAction = playerFlow.getCurrentMove();
        moveDistribution.setPlayerStatus(playerAction);
        moveDistribution.setTableCards(getCommonTableCards());

        return moveDistribution;
    }

    @Override
    public TopicDistributionTO chooseTopic(long playerId, long cardId, String title) {
        PlayerFlowMock playerFlow = playersFlows.get(playerId);

        topic.setAuthor(playerFlow.userData);
        topic.setTitle(title);

        UserCardTO topicCard = new UserCardTO();
        topicCard.setCard(getPlayerCard(playerFlow, cardId));
        topicCard.setUser(playerFlow.userData);

        tableCards.add(topicCard);

        return generateNextTopicDistribution(playerId);
    }

    @Override
    public TableCardsDistributionTO addTableCard(long playerId, long cardId) {
        UserTO userData = playersFlows.get(playerId).userData;
        UserCardTO userCard = new UserCardTO();
        userCard.setUser(userData);
        userCard.setCard(generateCard(cardId));
        tableCards.add(userCard);
        return generateNextTableCardsDistribution(userData.getId());
    }

    @Override
    public VotesDistributionTO makeVote(long playerId, long cardId) {
        VoteTO vote = new VoteTO();
        vote.setVoter(playersFlows.get(playerId).userData);
        vote.setOwner(getCardOwner(cardId));
        vote.setVoterBonus(randomGenerator.nextInt(10));
        vote.setOwnerBonus(randomGenerator.nextInt(10));
        votes.put(cardId, vote);

        return generateNextVotesDistribution(playerId);
    }

    @Override
    public VotesDistributionTO getVotes(long playerId) {
        PlayerStatusTO playerStatus = null;
        List<UserTO> rivals = new ArrayList<>();

        for(PlayerFlowMock playerFlow : playersFlows.values()) {
            if(playerFlow.userData.getId() == playerId) {
                playerStatus = playerFlow.getCurrentMove();
            } else if(playerFlow.getCurrentMove() == PlayerStatusTO.WAIT_PARTY_VOTES) {
                rivals.add(playerFlow.userData);
            }
        }

        VotesDistributionTO distribution = new VotesDistributionTO();
        distribution.setVotesPerCard(votes);
        distribution.setReadyRivals(rivals);
        distribution.setPlayerStatus(playerStatus);
        return distribution;
    }

    private PlayerTO getPlayerData(long playerId) {
        PlayerTO player = new PlayerTO();
        PlayerFlowMock playerFlow = playersFlows.get(playerId);
        player.setData(playerFlow.userData);
        player.setCards(playerFlow.cards);
        player.setStatus(playerFlow.getCurrentMove());
        return player;
    }

    private UserCardTO generateUserCard(long index, long playerId) {
        UserCardTO card = new UserCardTO();
        card.setCard(generateCard(index));
        card.setUser(getUserData(playerId));
        return card;
    }

    private CardTO generateCard(long index) {
        CardTO card = new CardTO();
        card.setId(index);
        card.setLink("/zenki/resources/img/cards/"+index+".png");
        return card;
    }

    private UserTO generateUser(long rivalId, String name, ColorSchemaTO schema) {
        UserTO rival = new UserTO();
        rival.setId(rivalId);
        rival.setName(name);
        rival.setColorSchema(schema);
        return rival;
    }

    private PlayerFlowMock generatePlayerMock(int participantsNumber, UserTO userData) {
        PlayerFlowMock result;
        if(participantsNumber == 0) {
            result = generateFirstPlayerDistribution(userData);
        } else if(participantsNumber == 1){
            result = generateSecondPlayerDistribution(userData);
        } else {
            result = generateAnyOtherPlayerDistribution(userData);
        }
        result.cards = generatePlayerCards(playersFlows.size());

        if(result.getCurrentMove() == PlayerStatusTO.CHOOSE_TOPIC) {
            topic.setAuthor(result.userData);
        }

        return result;
    }

    private List<CardTO> generatePlayerCards(int participantsNumber) {
        List<CardTO> cards = new ArrayList<>();
        int startIndex = CARDS_LIMIT*(participantsNumber%2) + 1;
        for(int index = startIndex; index<startIndex+CARDS_LIMIT; index++) {
            cards.add(generateCard(index));
        }
        return cards;
    }

    private PlayerFlowMock generateFirstPlayerDistribution(UserTO userData) {
        PlayerFlowMock firstPlayer = new PlayerFlowMock();
        firstPlayer.moveIndex = START_MOVE_INDEX;
        firstPlayer.userData = userData;
        firstPlayer.actionsFlow = Arrays.asList(
                PlayerStatusTO.WAIT_USER_TOPIC,
                PlayerStatusTO.ADD_CARD,
                //PlayerStatusTO.WAIT_PARTY_CARDS,
                PlayerStatusTO.MAKE_VOTE,
                //PlayerStatusTO.WAIT_PARTY_VOTES,
                PlayerStatusTO.VIEW_VOTES
        );
        return firstPlayer;
    }

    private PlayerFlowMock generateSecondPlayerDistribution(UserTO userData) {
        PlayerFlowMock secondPlayer = new PlayerFlowMock();
        secondPlayer.moveIndex = START_MOVE_INDEX;
        secondPlayer.userData = userData;
        secondPlayer.actionsFlow = Arrays.asList(
                PlayerStatusTO.CHOOSE_TOPIC,
                PlayerStatusTO.WAIT_PARTY_CARDS,
                PlayerStatusTO.WAIT_PARTY_VOTES,
                PlayerStatusTO.VIEW_VOTES
        );
        return secondPlayer;
    }

    private PlayerFlowMock generateAnyOtherPlayerDistribution(UserTO userData) {
        PlayerFlowMock anyOtherPlayer = new PlayerFlowMock();
        anyOtherPlayer.moveIndex = START_MOVE_INDEX;
        anyOtherPlayer.userData = userData;
        anyOtherPlayer.actionsFlow = Arrays.asList(
                PlayerStatusTO.WAIT_USER_TOPIC,
                PlayerStatusTO.WAIT_PARTY_CARDS,
                PlayerStatusTO.WAIT_PARTY_VOTES,
                PlayerStatusTO.VIEW_VOTES
        );
        return anyOtherPlayer;
    }

    private TopicDistributionTO generateNextTopicDistribution(long playerId) {
        moveOnFor(playerId, PlayerStatusTO.WAIT_USER_TOPIC);

        TopicDistributionTO distribution = new TopicDistributionTO();
        distribution.setTopic(topic);
        distribution.setPlayerStatus(playersFlows.get(playerId).getCurrentMove());

        return distribution;
    }

    private TableCardsDistributionTO generateNextTableCardsDistribution(long playerId) {
        moveOnFor(playerId, PlayerStatusTO.WAIT_PARTY_CARDS);

        TableCardsDistributionTO distribution = new TableCardsDistributionTO();
        distribution.setTableCards(getCommonTableCards());
        distribution.setPlayerStatus(playersFlows.get(playerId).getCurrentMove());

        return distribution;
    }

    private VotesDistributionTO generateNextVotesDistribution(long playerId) {
        long playerToChange = (votes.size() == (playersFlows.size()-1) ? playerId : -1);
        moveOnFor(playerToChange, PlayerStatusTO.WAIT_PARTY_VOTES);

        List<UserTO> readyRivals = new ArrayList<>();
        for(VoteTO vote : votes.values()) {
            if(vote.getVoter().getId() != playerId) {
                readyRivals.add(vote.getVoter());
            }
        }

        VotesDistributionTO distribution = new VotesDistributionTO();
        distribution.setVotesPerCard(votes);
        distribution.setReadyRivals(readyRivals);
        distribution.setPlayerStatus(playersFlows.get(playerId).getCurrentMove());

        return distribution;
    }

    private void moveOnFor(long playerId, PlayerStatusTO userAction) {
        for(PlayerFlowMock distribution : playersFlows.values()) {
            if(distribution.userData.getId() == playerId || distribution.getCurrentMove() == userAction) {
                distribution.moveIndex++;
            }
        }
    }

    private List<CardTO> getCommonTableCards() {
        //todo: add google collections dependency and use transformations methods
        List<CardTO> commonCards = new ArrayList<>();
        for(UserCardTO card : tableCards) {
            commonCards.add(card.getCard());
        }
        return commonCards;
    }

    private List<UserTO> getParticipants() {
        List<UserTO> participants = new ArrayList<>();
        for(PlayerFlowMock playerData : playersFlows.values()) {
            participants.add(playerData.userData);
        }
        return participants;
    }

    private ColorSchemaTO generateRandomColorSchema() {
        ColorSchemaTO colorSchema;
        ColorSchemaTO[] colorSchemas = ColorSchemaTO.values();
        do {
            int schemaIndex = randomGenerator.nextInt(colorSchemas.length);
            colorSchema = colorSchemas[schemaIndex];
        } while (isDuplicatedColor(colorSchema));
        return colorSchema;
    }

    private boolean isDuplicatedColor(ColorSchemaTO colorSchema) {
        for(PlayerFlowMock playerFlow : playersFlows.values()) {
            if(playerFlow.userData.getColorSchema() == colorSchema) {
                return true;
            }
        }
        return false;
    }

    private UserTO getCardOwner(long cardId) {
        for(PlayerFlowMock player : playersFlows.values()) {
            for (CardTO card : player.cards) {
                if(card.getId() == cardId) {
                    return  player.userData;
                }
            }
        }
        return null;
    }


    private CardTO getPlayerCard(PlayerFlowMock player, long cardId) {
        for (CardTO card : player.cards) {
            if(card.getId() == cardId) {
                return  card;
            }
        }
        return null;
    }

    public class PlayerFlowMock {
        UserTO userData;
        List<CardTO> cards;
        int moveIndex;
        List<PlayerStatusTO> actionsFlow;

        public PlayerStatusTO getCurrentMove() {
            return actionsFlow.get(moveIndex);
        }
    }
}
