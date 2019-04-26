package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.events.BaseResultEvent;
import com.tufike.taxi.common.models.Travel;

public class AcceptOrderResultEvent extends BaseResultEvent {
    public Travel travel;
    public AcceptOrderResultEvent(Object... args) {
        super(args);
        travel = Travel.fromJson(args[0].toString());
    }
}
