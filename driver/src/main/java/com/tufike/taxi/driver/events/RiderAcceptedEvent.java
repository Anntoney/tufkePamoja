package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.models.Travel;

public class RiderAcceptedEvent {
    public Travel travel;

    public RiderAcceptedEvent(Object... args) {
        this.travel = Travel.fromJson(args[0].toString());
    }
}
