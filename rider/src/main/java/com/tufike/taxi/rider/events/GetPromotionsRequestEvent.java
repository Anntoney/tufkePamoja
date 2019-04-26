package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.events.BaseRequestEvent;

public class GetPromotionsRequestEvent extends BaseRequestEvent {
    public GetPromotionsRequestEvent() {
        super(new GetPromotionsResultEvent());
    }
}
