package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.events.BaseRequestEvent;

public class GetRequestsRequestEvent extends BaseRequestEvent {
    public GetRequestsRequestEvent() {
        super(new GetRequestsResultEvent());
    }
}
