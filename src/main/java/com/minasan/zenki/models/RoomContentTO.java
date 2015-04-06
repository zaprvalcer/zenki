package com.minasan.zenki.models;

import java.util.List;

public class RoomContentTO {
    private boolean isRoomFull;
    private List<UserTO> users;

    public boolean getIsRoomFull() {
        return isRoomFull;
    }

    public void setIsRoomFull(boolean isCrowded) {
        this.isRoomFull = isCrowded;
    }

    public List<UserTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserTO> users) {
        this.users = users;
    }
}
