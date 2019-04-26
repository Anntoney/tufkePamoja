package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.models.Travel;

public class SendTravelInfoEvent {
    public Travel travel;
    public SendTravelInfoEvent(Travel travel) {
        this.travel = travel;
    }
}
