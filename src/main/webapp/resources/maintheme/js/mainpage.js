var REQUESTS_TIMEOUT = 3000;
$(document).ready(function() {
    $('#mp_greetings').html((USER_DATA == null ? localization['greet.anonymous'] : localization['greet.user'] + USER_DATA.name));
    $('#mp_login_description').html(localization['sign_in.description']);
    $('#mp_login_button').html(localization['mainpage.button.sign_in']);
    updatePageRoomContent();
});

function updatePageRoomContent() {
    $.ajax({
        url: APPLICATION_BASE_URL+"/data/room/content",
        success: function(result) {
            if(USER_DATA != null) {
                processRegisteredUserResponse(result);
            } else {
                processAnonymousUserResponse(result);
            }
        }
    });
}

function processRegisteredUserResponse(result) {
    if(result.roomStatus == 'CROWDED') {
        window.location.href = APPLICATION_BASE_URL+"/gamescreen?pid="+USER_DATA.id;
    } else {
        if($("#mp_room_status").attr('roomStatus') != result.roomStatus) {
            $("#mp_registration_form").hide();
            $("#mp_room_status").html(localization['wait.join']);
            $("#mp_room_status").attr('roomStatus', result.roomStatus);
        }
        if($('.mp_participant').length != result.participants.length) {
            reloadUsersList(result.participants);
        }
        setTimeout(updatePageRoomContent(), REQUESTS_TIMEOUT);
    }
}

function processAnonymousUserResponse(result) {
    var roomStatusOnPage = $("#mp_room_status").attr('roomStatus');
    if(result.roomStatus != roomStatusOnPage){
        changeRoomStatus(result);
    }
    if(result.roomStatus != 'CROWDED'){
        $("#mp_registration_form").show();
    }
    reloadUsersList(result.participants);
}

function changeRoomStatus(result) {
    $("#mp_room_status").html(localization['room.status.'+result.roomStatus]);
    $("#mp_room_status").attr('roomStatus', result.roomStatus);
}

function reloadUsersList(participants) {
    $("#mp_participants").empty();
    for(var index=0; index<participants.length; index++) {
        $("#mp_participants").append("<div class='mp_participant'>"+participants[index].name+"</div>");
    }
}