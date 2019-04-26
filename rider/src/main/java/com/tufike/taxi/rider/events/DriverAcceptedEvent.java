package com.tufike.taxi.rider.events;

import com.google.gson.Gson;
import com.tufike.taxi.common.models.Driver;

public class DriverAcceptedEvent {
    public Driver driver;
    public DriverAcceptedEvent(Object... args) {
        Gson gson = new Gson();
        driver = gson.fromJson(args[0].toString(), Driver.class);
    }
}
