package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.models.Rider;
import com.tufike.taxi.common.events.BaseResultEvent;

public class LoginResultEvent extends BaseResultEvent {
    public Rider rider;
    public String riderJson;
    public String jwtToken;
    public LoginResultEvent(int response, String riderJson, String jwtToken) {
        super(response);
        this.riderJson = riderJson;
        this.rider = new Rider().fromJson(riderJson);
        this.jwtToken = jwtToken;
    }
    public LoginResultEvent(int response, String message) {
        super(response,message);
    }
}
