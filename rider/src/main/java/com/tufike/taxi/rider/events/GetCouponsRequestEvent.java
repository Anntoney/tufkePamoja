package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.events.BaseRequestEvent;

public class GetCouponsRequestEvent extends BaseRequestEvent {
    public GetCouponsRequestEvent() {
        super(new GetCouponsResultEvent());
    }
}
