package com.minasan.zenki.models;

import java.util.List;

public class RoomStatusModel {
    private boolean isRoomFull;
    private List<String> players;

    public boolean getIsRoomFull() {
        return isRoomFull;
    }

    public void setIsRoomFull(boolean isRoomFull) {
        this.isRoomFull = isRoomFull;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }
}
