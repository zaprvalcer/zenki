<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.minasan.zenki.models.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="resources/js/jquery-1.11.2.min.js"></script>
    <script src="resources/js/mainpage.js"></script>
    <script type="text/javascript">
        <%UserTO player = (UserTO)request.getAttribute("player");%>
        var APPLICATION_BASE_URL = 'http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}',
        <% if(player != null) {%>
            USER_DATA = {
                'id' : '<%=player.getId()%>',
                'name' : '<%=player.getName()%>',
                'schema' : '<%=player.getColorSchema()%>'
            };
        <%} else {%>
            USER_DATA = null;
        <%}%>

        $(document).ready(loadMainPageContent);
    </script>
</head>
<body>
<h1 id="mp_greetings">Wanna play a game?</h1>
<h2 id="mp_room_status"></h2>
<div id="mp_participants"></div>
<br/><br/>
<form id="mp_registration_form" action="mainpage" method="post" hidden="true">
    Sign in to join the game:
    <input id="mp_login_name" name="userName" type="text" style="width: 100px; height:20px;"/>
    <button id="mp_login_button" type="submit" style="width: 100px; height:20px;">Sign in</button>
</form>

</body>
</html>