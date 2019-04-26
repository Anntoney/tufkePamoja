package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.models.Request;
import com.tufike.taxi.common.models.TravelSerializable;

public class RequestReceivedEvent {
    public Request request;

    public RequestReceivedEvent(String travelJson,Integer travelDistance,Integer fromDriver, Double cost) {
        this.request = new Request(TravelSerializable.fromJson(travelJson),travelDistance,fromDriver,cost);
    }
}