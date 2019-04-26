package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.events.BaseRequestEvent;
import com.tufike.taxi.common.utils.ServerResponse;

public class ChangeHeaderImageEvent extends BaseRequestEvent {
    public String path;
    public ChangeHeaderImageEvent(String path){
        super(new ChangeHeaderImageResultEvent(ServerResponse.REQUEST_TIMEOUT.getValue(),null));
        this.path = path;
    }
}
