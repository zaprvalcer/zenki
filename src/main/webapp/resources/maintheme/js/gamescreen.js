var REQUESTS_TIMEOUT = 3000;

function generatePageContent(playerStatus) {
    $('#gs_player_CHOOSE_TOPIC_button').click(postRoundTopic);
    $('#gs_player_CHOOSE_TOPIC_button').html(localization['gamescreen.button.choose_topic.approve']);
    $('#gs_player_VIEW_VOTES_button').html(localization['gamescreen.button.view_votes.approve']);

    updatePageDueToMoveStatus(playerStatus);
}

//This were it goes when F5 is pressed
//uploads data for player status and show it
function updatePageDueToMoveStatus(playerStatus) {
    $('#gs_move_tooltip').html(localization['player.action.tooltip.'+playerStatus]);
    switch(playerStatus) {
        case 'CHOOSE_TOPIC':
            clearRound();
            switchToChooseTopicSupport();
            break;
        case 'WAIT_USER_TOPIC':
            clearRound();
            fetchTopicStatus();
            break;
        case 'ADD_CARD':
            switchToAddCardSupport();
            break;
        case 'WAIT_PARTY_CARDS':
            //maybe method should receive the distribution and here pass the cards
            waitPartyCards();
            break;
        case 'MAKE_VOTE':
            //maybe method should receive the distribution and here pass the cards
            switchToVoteSupport();
            break;
        case 'WAIT_PARTY_VOTES':
            waitPartyVotes();
            break;
        case 'VIEW_VOTES':
            showRoundResults();
            break;
    }
}

function clearRound() {
    $('#gs_cards_table').html('');
    $('#gs_round_topic').html('');
}

function showRoundResults() {
    $('#gs_player_VIEW_VOTES_button').click(function(){
        //new round or end game
    });
    $('#gs_player_VIEW_VOTES').show();
}

function fetchTopicStatus() {
    $.ajax({
        url: APPLICATION_BASE_URL+"/data/player/"+PLAYER_ID+"/topic",
        success: function(distribution) {
            var topic = distribution.topic;
            if(topic.title == null) {
                if($('#gs_round_topic').text() == '') {
                    $('#gs_round_topic').html(topic.author.name + localization['user.choosing.topic']);
                }
                setTimeout(fetchTopicStatus, REQUESTS_TIMEOUT);
            } else {
                updatePageTopic(topic);
                updatePageDueToMoveStatus(distribution.playerStatus);
            }
        }
    });
}

function updatePageTopic(topic) {
    $('#gs_round_topic').text(topic.author.name+": \""+topic.title+"\"");
    $('#gs_cards_table').html('<div class="gs_game_card gs_table_card gs_closed_card"></div>');
}

function updatePageVotes(votes) {
    $.each(votes, function(cardId, vote){
        $('#gs_table_card_'+cardId).addClass(vote.owner.colorSchema);
        $('#gs_player_'+vote.voter.id+' .gs_player_bonus').text('+'+vote.voterBonus);
    });
}

function waitPartyCards() {
    $.ajax({
        url: APPLICATION_BASE_URL+"/data/player/"+PLAYER_ID+"/table_cards",
        success: function(distribution) {
            if(distribution.playerStatus == 'WAIT_PARTY_CARDS') {
                updateClosedPageTableCards(distribution.tableCards);
                setTimeout(waitPartyCards, REQUESTS_TIMEOUT);
            } else {
                updatePageTableCards(distribution.tableCards);
                updatePageDueToMoveStatus(distribution.playerStatus);
            }
        }
    });
    //todo:s update names of users to wait
}

function waitPartyVotes() {
    $.ajax({
        url: APPLICATION_BASE_URL+"/data/player/"+PLAYER_ID+"/votes",
        success: function(distribution) {
            if(distribution.playerStatus == 'WAIT_PARTY_VOTES') {
                //showFinishedPlayers(distribution.readyRivals);
                setTimeout(waitPartyVotes, REQUESTS_TIMEOUT);
            } else {
                //updatePageTableVoteResults(distribution.votesPerCard);
            }
        }
    });
}

function switchToChooseTopicSupport() {
    $('.gs_player_card:first').addClass('gs_chosen_card');
    $('#gs_round_topic').html(localization['player.choosing.topic']);
    $('#gs_player_CHOOSE_TOPIC').show();
    $('.gs_player_card').click(function(){
        $('.gs_player_card').removeClass('gs_chosen_card');
        $(this).addClass('gs_chosen_card');
    });
}

function resetChooseTopicSupport() {
    $('#gs_player_CHOOSE_TOPIC_text').val('');
    $('.gs_player_card').removeClass('gs_chosen_card').unbind('click');
}

function switchToAddCardSupport() {
    $('.gs_player_card').click(function(){
        if($(this).hasClass('gs_chosen_card')) {
            postTableCard($(this).attr('img_id'));
        } else {
            $('.gs_player_card').removeClass('gs_chosen_card');
            $(this).addClass('gs_chosen_card');
        }
    });
}

function resetAddCardSupport() {
    $('.gs_player_card.gs_chosen_card').unbind('click');
    $('.gs_player_card').removeClass('gs_chosen_card').unbind('click');
}

function switchToVoteSupport() {
    $('.gs_table_card').click(function(){
        if($(this).hasClass('gs_chosen_card')) {
            postVoteCard($(this).attr('img_id'))
        } else {
            $('.gs_table_card').removeClass('gs_chosen_card');
            $(this).addClass('gs_chosen_card');
        }
    });
    $('.gs_table_card.gs_chosen_card').click(postVoteCard);
}

function resetVoteSupport() {
    $('.gs_table_card.gs_chosen_card').unbind('click');
    $('.gs_table_card').removeClass('gs_chosen_card').unbind('click');
}

function postRoundTopic() {
    var cardId = $('.gs_player_card.gs_chosen_card').attr('img_id'),
        title = $('#gs_player_CHOOSE_TOPIC_text').val();
    resetChooseTopicSupport();
    $.ajax({
        url: APPLICATION_BASE_URL+"/data/player/"+PLAYER_ID+"/card/"+cardId+"/topic",
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: title,
        dataType: 'json',
        success: function(result){
            $('#gs_player_CHOOSE_TOPIC').hide();
            updatePageTopic(result.topic);
            updatePageDueToMoveStatus(result.playerStatus);
        }
        //todo: process failures too
    });
}

function postTableCard(cardId) {
    resetAddCardSupport();
    $.ajax({
        url: APPLICATION_BASE_URL+"/data/player/"+PLAYER_ID+"/card/"+cardId+"/add",
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function(distribution){
            if(distribution.playerStatus = 'WAIT_PARTY_CARDS') {
                updateClosedPageTableCards(distribution.tableCards);
            } else {
                updateClosedPageTableCards(distribution.tableCards);
            }
            updatePageDueToMoveStatus(distribution.playerStatus);
        }
        //todo: process failures too
    });
}

function updatePageTopic(topic) {
    $('#gs_round_topic').text(topic.author.name+":"+topic.title);
}

function updateClosedPageTableCards(tableCards) {
    var currentTableCardsNumber = $('.gs_table_card').size();
    for(currentTableCardsNumber; currentTableCardsNumber<tableCards.length; currentTableCardsNumber++) {
        $('#gs_cards_table').append("<div class='gs_game_card gs_table_card gs_closed_card'></div>");
    }
}

function updatePageTableCards(tableCards) {
    var tableCardsList = '';
    $.each(tableCards, function(index, tableCard){
        tableCardsList += "<div id='gs_table_card_"+tableCard.id+"'class='gs_game_card gs_table_card' img_id='"+tableCard.id+"'><img src='"+tableCard.link+"'/></div>";
    });
    $('#gs_cards_table').html(tableCardsList);
}

function postVoteCard(cardId) {
    resetVoteSupport();
    $.ajax({
        url: APPLICATION_BASE_URL+"/data/player/"+PLAYER_ID+"/card/"+cardId+"/vote",
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function(distribution){
            updatePageVotes(distribution.votesPerCard);
            updatePageDueToMoveStatus(distribution.playerStatus);
        }
    });
}