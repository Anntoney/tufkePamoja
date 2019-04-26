package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.events.BaseResultEvent;

public class ServiceRequestResultEvent extends BaseResultEvent {
    public int driversSentTo;
    public ServiceRequestResultEvent(int driversSentTo) {
        super(200);
        this.driversSentTo = driversSentTo;
    }
}
