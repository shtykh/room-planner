package shtykh.roomplanner.service.impl;

import org.springframework.stereotype.Repository;
import shtykh.roomplanner.model.RoomLevel;
import shtykh.roomplanner.service.RoomStateService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

@Repository
public class RoomStateServiceImpl implements RoomStateService {

    private final Object lock = new Object();
    private Map<RoomLevel, Integer> availableRooms = Collections.emptyMap();

    @Override
    public Map<RoomLevel, Integer> getAvailableRooms() {
        synchronized (lock) {
            return availableRooms;
        }
    }

    @Override
    public void setAvailableRooms(Map<RoomLevel, Integer> availabilities) {
        synchronized (lock) {
            this.availableRooms = unmodifiableMap(new HashMap<>(availabilities));
        }
    }
}
