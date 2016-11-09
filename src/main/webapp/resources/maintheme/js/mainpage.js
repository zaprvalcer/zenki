function loadMainPageContent() {
    var greetings = (USER_DATA == null ? 'Wanna play a game?' : 'Hi, ' + USER_DATA.name);
    $('#mp_greetings').html(greetings);
    requestRoomContent();
}

function requestRoomContent() {
    $.ajax({
        url: APPLICATION_BASE_URL+"/data/room/content",
        success: updatePageRoomContent
    });
}

function updatePageRoomContent(result) {
    if(USER_DATA != null) {
        if(result.isRoomFull) {
            window.location.href = APPLICATION_BASE_URL+"/game/"+USER_DATA.id;
        } else {
            $("#mp_registration_form").hide();
            $("#mp_room_status").html('Waiting for other players to join...');
            $("#mp_room_status").attr('isCrowded', result.isRoomFull);
            reloadUsersList(result.users);
        }
    } else {
        var isCrowdedOnPage = $("#mp_room_status").attr('isCrowded');
        if(isCrowdedOnPage == null || result.isRoomFull != JSON.parse(isCrowdedOnPage)){
            changeRoomStatus(result);
            reloadUsersList(result.users);
        } else if(!result.isRoomFull){
            reloadUsersList(result.users);
        }
    }
}

function changeRoomStatus(result) {
    var roomStatus;
    if(result.isRoomFull) {
        $("#mp_registration_form").hide();
        roomStatus = "Sorry, we have no free places. Try again later";
    } else {
        $("#mp_registration_form").show();
        roomStatus = "Join the club:";
    }

    $("#mp_room_status").html(roomStatus);
    $("#mp_room_status").attr('isCrowded', result.isRoomFull);
}

function reloadUsersList(users) {
    $("#mp_participants").empty();
    for(var index=0; index<users.length; index++) {
        $("#mp_participants").append("<div>"+users[index].name+"</div>");
    }
}