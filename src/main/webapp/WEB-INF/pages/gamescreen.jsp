<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.minasan.zenki.models.*" %>
<html>
<head>
    <link rel="stylesheet" href="resources/css/gamescreen.css" type="text/css" media="all"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="resources/js/jquery-1.11.2.min.js"></script>
    <script src="resources/js/localization.js"></script>
    <script type="text/javascript">
        <%
            GameDistributionTO distribution = (GameDistributionTO) request.getAttribute("distribution");
            PlayerTO player = distribution.getPlayer();
            TopicTO topic = distribution.getTopic();
        %>
        var APPLICATION_BASE_URL = 'http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}',
            PLAYER_ID = <%=player.getData().getId()%>;

        $(document).ready(function() {
            generatePageContent('<%=player.getStatus()%>');
        });
    </script>
    <script src="resources/js/gamescreen.js"></script>
</head>
<body>
<input type="hidden" id="gs_move_status" status="">
<div id="gs_rivals">
    <% for(UserTO rival : distribution.getParty()) {%>
        <div id="gs_player_<%=rival.getId()%>" class="gs_rival <%=rival.getColorSchema()%>">
            <div class="gs_player_name"><%=rival.getName()%></div>
            <div class="gs_player_rate"><%=rival.getRate()%></div>
            <div class="gs_player_bonus"></div>
        </div>
    <%}%>
</div>
<h2 id="gs_round_topic"><%=topic.getAuthor().getName()%>: "<%=topic.getTitle()%>"</h2>
<div id="gs_cards_table"></div>
<div id="gs_player_tools" class="<%=player.getData().getColorSchema()%>">
    <div id="gs_player_<%=player.getData().getId()%>">
        <div class="gs_player_name"><%=player.getData().getName()%></div>
        <div class="gs_player_rate"><%=player.getData().getRate()%></div>
        <div class="gs_player_bonus"></div>
    </div>
    <div id="gs_move_tooltip"></div>
    <div id="gs_player_CHOOSE_TOPIC" hidden="true">
        <input id="gs_player_CHOOSE_TOPIC_text" type="text"/>
        <button id="gs_player_CHOOSE_TOPIC_button"></button>
    </div>
    <div id="gs_player_VIEW_VOTES" hidden="true">
        <button id="gs_player_VIEW_VOTES_button"></button>
    </div>
</div>
<div id="gs_player_cards">
    <%for(CardTO card : player.getCards()){%>
    <div class="gs_game_card gs_player_card" img_id="<%=card.getId()%>">
        <img src="<%=card.getLink()%>"/>
    </div>
    <%}%>
</div>

</body>
</html>