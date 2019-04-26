package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.events.BaseRequestEvent;
import com.tufike.taxi.common.utils.ServerResponse;

public class AcceptDriverEvent extends BaseRequestEvent {
    public int driverId;
    public AcceptDriverEvent(int driverId){
        super(new AcceptDriverResultEvent(ServerResponse.REQUEST_TIMEOUT.getValue(),0f,0f,0f,0f));
        this.driverId = driverId;
    }
}
