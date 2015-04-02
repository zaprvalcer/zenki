<html>
<!--todo: move javascripts to separate files in separate folder-->
<!--todo: css and stylesheets as well-->
<!--todo: think of the better way to store and use API paths-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var applicationBaseUrl = 'http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/';
            $.ajax({
                url: applicationBaseUrl+"/data/roomStatus",
                success: function(result){
                    console.log(result);
                    var roomStatus = (result.isRoomFull ? 'Try to join later' : 'You can join');
                    $("#roomStatus").html(roomStatus);
                    $("#playersList").html('First player in da house is '+result.players[0]);
                }
            });
        });
    </script>
</head>
<body>
<h1>Greetings, dear user</h1>

<h2>${message}</h2>

<h3 id="roomStatus"></h3>
<div id="playersList"></div>

</body>
</html>