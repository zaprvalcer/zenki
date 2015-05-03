package com.minasan.zenki.models;

import java.util.List;

public class RoomContentTO {
    private RoomStatusTO roomStatus;
    private List<UserTO> participants;

    public RoomStatusTO getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatusTO roomStatus) {
        this.roomStatus = roomStatus;
    }

    public List<UserTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserTO> participants) {
        this.participants = participants;
    }
}
