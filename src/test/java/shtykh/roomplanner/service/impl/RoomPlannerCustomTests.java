package shtykh.roomplanner.service.impl;

import org.junit.Test;
import shtykh.roomplanner.model.RoomLevel;
import shtykh.roomplanner.model.RoomPlan;
import shtykh.roomplanner.service.RoomPlanner;

import java.util.*;

import static java.util.Arrays.asList;
import static shtykh.roomplanner.model.RoomLevel.ECONOMY;
import static shtykh.roomplanner.model.RoomLevel.PREMIUM;

public class RoomPlannerCustomTests extends RoomPlannerTests {

    @Test(expected = IllegalArgumentException.class) // FIXME
    public void nullPayments() throws Exception {
        Map<RoomLevel, Integer> availabilities = new HashMap<RoomLevel, Integer>(2) {{
            put(PREMIUM, 7);
            put(ECONOMY, 1);
        }};
        RoomPlan expectedPlan = () -> asList(roomUsage(PREMIUM, 0, 0),
                                             roomUsage(ECONOMY, 0, 0));
        verifyPlan(availabilities, expectedPlan, null);
    }

    @Test
    public void emptyPayments() throws Exception {
        List<Integer> request = Collections.emptyList();
        Map<RoomLevel, Integer> availabilities = new HashMap<RoomLevel, Integer>(2) {{
            put(PREMIUM, 7);
            put(ECONOMY, 1);
        }};
        RoomPlan expectedPlan = () -> asList(roomUsage(PREMIUM, 0, 0),
                                             roomUsage(ECONOMY, 0, 0));
        verifyPlan(availabilities, expectedPlan, request);
    }

    @Test
    public void emptyAvailable() throws Exception {
        List<Integer> request = Arrays.asList(100,101,102);
        Map<RoomLevel, Integer> availabilities = Collections.emptyMap();
        RoomPlan expectedPlan = () -> asList(roomUsage(PREMIUM, 0, 0),
                                             roomUsage(ECONOMY, 0, 0));
        verifyPlan(availabilities, expectedPlan, request);
    }

    @Override
    protected RoomPlanner getPlannerService() {
        return new RoomPlannerHeapImpl(new RoomStateServiceImpl());
    }
}