package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.events.BaseRequestEvent;
import com.tufike.taxi.common.utils.ServerResponse;

public class ServiceFinishEvent extends BaseRequestEvent {
    public Double cost;
    public int duration;
    public int distance;
    public String log;
    public ServiceFinishEvent(Double cost, int duration, int distance,String log) {
        super(new ServiceFinishResultEvent(ServerResponse.REQUEST_TIMEOUT.getValue(),false,0f));
        this.cost = cost;
        this.duration = duration;
        this.distance = distance;
        this.log = log;
    }
}
