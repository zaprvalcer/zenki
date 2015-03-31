package com.minasan.zenki.services;

import com.minasan.zenki.models.RoomStatusModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MocksGeneratorService {
    public RoomStatusModel generateRoomStatus() {
        RoomStatusModel roomStatus = new RoomStatusModel();
        roomStatus.setIsRoomFull(true);
        roomStatus.setPlayers(Arrays.asList("Led", "Zeppelin"));
        return roomStatus;
    }
}
