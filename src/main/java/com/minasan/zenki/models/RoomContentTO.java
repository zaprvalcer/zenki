package com.minasan.zenki.models;

import java.util.List;

public class RoomContentTO {
    private boolean isFilled;
    private List<UserTO> users;

    public boolean getIsFilled() {
        return isFilled;
    }

    public void setIsFilled(boolean isCrowded) {
        this.isFilled = isCrowded;
    }

    public List<UserTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserTO> users) {
        this.users = users;
    }
}
